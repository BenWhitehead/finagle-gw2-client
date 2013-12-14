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

import com.github.benwhitehead.gw2.api.model._
import com.twitter.util.Await
import java.util.UUID
import org.scalatest.FreeSpec

/**
 * @author Ben Whitehead
 */
class GuildWars2ApiClientSpec extends FreeSpec {
  implicit def uuid(uuid: String): UUID = UUID.fromString(uuid)

  "Guild Wars 2 Api Client should" - {
    val client = new GuildWars2ApiClient(
      GuildWars2ApiRestClient(hostConnectionLimit = 30),
      new GuildWars2ApiRequestFactory()
    )

    "fetch" - {
      "Guild Details" - {
        "by id" in {
          val f = client.fetchGuildDetails(uuid("75FD83CF-0C45-4834-BC4C-097F93A487AF")) onSuccess {
            case guild =>
              assert(guild.name === "Veterans Of Lions Arch")
              assert(guild.tag === "LA")
          }
          Await.result(f)
        }
        "by name" in {
          val f = client.fetchGuildDetails("Veterans Of Lions Arch") onSuccess {
            case guild =>
              assert(guild.id === uuid("75FD83CF-0C45-4834-BC4C-097F93A487AF"))
              assert(guild.tag === "LA")
          }
          Await.result(f)
        }
      }

      "World vs World" - {
        "matches" in {
          val f = client.fetchWorldVsWorldMatches() onSuccess {
            case matches =>
              assert(!matches.isEmpty)
          }
          Await.result(f)
        }
        "match details" in {
          val f = client.fetchWorldVsWorldMatchDetails("1-4") onSuccess {
            case matchDetails =>
              assert(matchDetails.scores.size === 3)
              assert(!matchDetails.maps.isEmpty)
          }
          Await.result(f)
        }
        "objective names" in {
          val f = client.fetchWorldVsWorldObjectNames() onSuccess {
            case objectiveNames =>
              objectiveNames.find { o => o.id == 30 } match {
                case Some(WorldVsWorldObjectName(30, "Tower")) => assert(true === true) // success
                case _ => fail()
              }
          }
          Await.result(f)
        }
      }

      "events for world 1015" in {
        val f = client.fetchEventsForWorld(1015) onSuccess {
          case events =>
            println(s"events.size = ${events.size}")
        }
        Await.result(f)
      }

      "worlds" in {
        val f = client.fetchAllWorlds() onSuccess {
          case worlds =>
            println(s"worlds.size = ${worlds.size}")
        }
        Await.result(f)
      }

      "map floor [continentId = 1, floor = 1]" in {
        val f = client.fetchMapFloor(1, 1) onSuccess {
          case mapFloor =>
            val regions = mapFloor.regions
            assert(regions.size === 6)
            val kryta = regions.find { t: (Int, WorldRegion) => t._2.name == "Kryta" }.get._2
            val lionsArch = kryta.maps.find { e: (Int, WorldMapFloorMap) => e._2.name == "Lion's Arch" }.get._2
            assert(lionsArch.pointsOfInterest.size > 0)
            assert(lionsArch.sectors.size > 0)
        }
        Await.result(f)
      }

      "recipes" in {
        val f = client.fetchAllRecipeDetails() onSuccess {
          case recipes =>
            println(s"recipes.size = ${recipes.size}")
        }
        Await.result(f)
      }

      "items" in {
        val f = client.fetchAllItemDetails() onSuccess {
          case items: Seq[Item] =>
            println(s"items.size = ${items.size}")
        }
        Await.result(f)
      }
    }
  }
}
