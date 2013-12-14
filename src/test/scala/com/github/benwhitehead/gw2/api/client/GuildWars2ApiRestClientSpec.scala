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

import com.github.benwhitehead.gw2.api.model.{Recipe, World}
import com.twitter.util.{Await, Future}
import org.scalatest.FreeSpec

/**
 * @author Ben Whitehead
 */
class GuildWars2ApiRestClientSpec extends FreeSpec {
  val rf = new GuildWars2ApiRequestFactory()

  "Guild Wars 2 API client should" - {
    val client = GuildWars2ApiRestClient(hostConnectionLimit = 30)
    def r[T](f: Future[T]): Future[T] = {
      f onFailure {
        case e =>
          fail(e)
      }
    }

    "deserialize" - {
      "World" in {
        val worlds = r(client[List[World]](rf.getWorldNames)) onSuccess {
          case worlds: List[World] => println(worlds.mkString(", "))
        }
        Await.all(worlds)
      }
      "Recipe 2408 (Berserker's Feathered Boots[11117])" in {
        val future = r(client[Recipe](rf.getRecipeDetails(2408))) onSuccess {
          case recipe: Recipe => println(recipe)
        }
        Await.all(future)
      }
    }
  }
}
