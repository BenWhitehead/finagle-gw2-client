/*
 * Copyright (c) 2013 Ben Whitehead.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.benwhitehead.gw2.api.client

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.conversions.time.intToTimeableNumber
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.http.Http
import com.twitter.finagle.{SimpleFilter, Service}
import com.twitter.util.{Duration, Future}
import java.lang.reflect.{Type, ParameterizedType}
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import org.jboss.netty.handler.codec.http.{HttpResponse, HttpRequest}
import org.jboss.netty.util.CharsetUtil._
import scala.Exception


/**
 * @author Ben Whitehead
 */
class GuildWars2ApiRestClient(client: Service[HttpRequest, HttpResponse]) {

  /**
   * Found at http://stackoverflow.com/a/14166997
   */
  object JacksonWrapper {
    val mapper = new ObjectMapper().registerModule(DefaultScalaModule)

    def serialize(value: Any): String = {
      import java.io.StringWriter
      val writer = new StringWriter()
      mapper.writeValue(writer, value)
      writer.toString
    }

    def deserialize[T: Manifest](value: String) : T =
      mapper.readValue(value, typeReference[T])

    private [this] def typeReference[T: Manifest] = new TypeReference[T] {
      override def getType = typeFromManifest(manifest[T])
    }

    private [this] def typeFromManifest(m: Manifest[_]): Type = {
      if (m.typeArguments.isEmpty) { m.erasure }
      else new ParameterizedType {
        def getRawType = m.erasure
        def getActualTypeArguments = m.typeArguments.map(typeFromManifest).toArray
        def getOwnerType = null
      }
    }
  }

  def apply[T: Manifest](httpRequest: HttpRequest): Future[T] = {
    client(httpRequest) flatMap { response =>
      Future.value(JacksonWrapper.deserialize[T](response.getContent.toString(UTF_8)))
    }
  }

  def releaseOnShutdown(): GuildWars2ApiRestClient = {
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() {
        client.close()
      }
    })
    this
  }
}

object GuildWars2ApiRestClient {
  def apply(
    hostName: String = "api.guildwars2.com",
    port: Int = 443,
    secure: Boolean = true,
    tcpConnectionTimeout: Duration = 2.seconds,
    requestTimeout: Duration = 30.seconds,
    hostConnectionLimit: Int = 5
    ): GuildWars2ApiRestClient = {
    var builder = ClientBuilder()
                  .codec(Http())
                  .hosts(s"$hostName:$port")
                  .tcpConnectTimeout(tcpConnectionTimeout)
                  .requestTimeout(requestTimeout)
                  .hostConnectionLimit(hostConnectionLimit)
    if (secure) builder = builder.tls(hostName)
    new GuildWars2ApiRestClient(new HandleErrors andThen builder.build()) releaseOnShutdown()
  }

  class InvalidRequest extends Exception

  /**
   * Convert HTTP 4xx and 5xx class responses into Exceptions.
   */
  class HandleErrors extends SimpleFilter[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest, service: Service[HttpRequest, HttpResponse]) = {
      service(request) flatMap { response =>
        response.getStatus match {
          case OK        => Future.value(response)
          case FORBIDDEN => Future.exception(new InvalidRequest)
          case _         => Future.exception(new Exception(response.getStatus.getReasonPhrase))
        }
      }
    }
  }
}
