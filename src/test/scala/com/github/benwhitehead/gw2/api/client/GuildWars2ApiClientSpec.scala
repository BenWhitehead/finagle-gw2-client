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

import org.scalatest.FreeSpec
import com.twitter.util.{Await, Future, Stopwatch}
import com.github.benwhitehead.gw2.api.model.Item

/**
 * @author Ben Whitehead
 */
class GuildWars2ApiClientSpec extends FreeSpec {
  "Guild Wars 2 Api Client should" - {
    val client = new GuildWars2ApiClient(
      GuildWars2ApiRestClient(hostConnectionLimit = 30),
      new GuildWars2ApiRequestFactory()
    )

    "fetch" - {
      "events for world 1015" in {
        val time = Stopwatch.start()
        val f = client.fetchAllEvents(1015) onSuccess {
          case events =>
            println(s"events.size = ${events.size}")
            println(time())
        }
        Await.result(f)
      }

      "worlds" in {
        val time = Stopwatch.start()
        val f = client.fetchAllWorlds() onSuccess {
          case worlds =>
            println(s"worlds.size = ${worlds.size}")
            println(time())
        }
        Await.result(f)
      }

      "items" in {
        val time = Stopwatch.start()
        val f = client.fetchAllItemDetails() onSuccess {
          case items: Seq[Item] =>
            println(s"items.size = ${items.size}")
            println(time())
        }
        Await.result(f)
      }

      "recipes" in {
        val time = Stopwatch.start()
        val f = client.fetchAllRecipeDetails() onSuccess {
          case recipes =>
            println(s"recipes.size = ${recipes.size}")
            println(time())
        }
        Await.result(f)
      }
    }

    "script" in {
      val time = Stopwatch.start()
      val f = Future.join(
        client.fetchAllRecipeDetails(),
        client.fetchAllItemDetails(),
        client.fetchAllWorlds(),
        client.fetchAllEvents(1015)
      ) flatMap {
        case (recipes, items, worlds, events) =>
          Future.value((recipes, items, worlds, events))
      } onSuccess {
        case (recipes, items, worlds, events) =>
          println(s"recipes.size = ${recipes.size}")
          println(s"items.size = ${items.size}")
          println(s"worlds.size = ${worlds.size}")
          println(s"events.size = ${events.size}")
          println(time())
      }
      Await.result(f)
    }
  }
}
