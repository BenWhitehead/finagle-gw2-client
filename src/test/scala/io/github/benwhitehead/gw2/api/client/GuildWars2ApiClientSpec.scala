/*
 * Copyright (c) 2014 Ben Whitehead.
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

package io.github.benwhitehead.gw2.api.client

import io.github.benwhitehead.gw2.api.model._
import com.twitter.conversions.time.longToTimeableNumber
import com.twitter.util.{Awaitable, Await}
import java.util.UUID
import org.scalatest.FreeSpec

/**
 * @author Ben Whitehead
 */
class GuildWars2ApiClientSpec extends FreeSpec {
  implicit def uuid(uuid: String): UUID = UUID.fromString(uuid)

  def await[T](a: Awaitable[T]) = Await.result(a, 15.seconds)

  "Guild Wars 2 Api Client should" - {
    val client = new GuildWars2ApiClient(
      GuildWars2ApiRestClient(hostConnectionLimit = 150),
      new GuildWars2ApiRequestFactory()
    )

    "fetch" - {
      "Guild Details" - {
        "by id" in {
          val guild = await(client.fetchGuildDetails(uuid("75FD83CF-0C45-4834-BC4C-097F93A487AF")))
          assert(guild.name === "Veterans Of Lions Arch")
          assert(guild.tag === "LA")
        }
        "by name" in {
          val guild = await(client.fetchGuildDetails("Veterans Of Lions Arch"))
          assert(guild.id === uuid("75FD83CF-0C45-4834-BC4C-097F93A487AF"))
          assert(guild.tag === "LA")
        }
      }

      "World vs World" - {
        "matches" in {
          val matches = await(client.fetchWorldVsWorldMatches())
          assert(matches.nonEmpty)
        }
        "match details" in {
          val matchDetails = await(client.fetchWorldVsWorldMatchDetails("1-4"))
          val size: Int = matchDetails.scores.size
          assert(size === 3)
          val maps: List[WorldVsWorldMapDetails] = matchDetails.maps
          assert(maps.exists { case w => w.`type` == "RedHome" })
          assert(maps.exists { case w => w.`type` == "GreenHome" })
          assert(maps.exists { case w => w.`type` == "BlueHome" })
          assert(maps.exists { case w => w.`type` == "Center" })
        }
        "objective names" in {
          val objectiveNames = await(client.fetchWorldVsWorldObjectNames())
          objectiveNames.find { o => o.id == 30 } match {
            case Some(WorldVsWorldObjectName(30, "Tower")) => assert(true === true) // success
            case _ => fail()
          }
        }
      }

      // Events disabled in api
//      "events for world 1015" in {
//        val events = await(client.fetchEventsForWorld(1015))
//        assert(events.nonEmpty)
//      }

      // World Names is disable in the API
//      "worlds" in {
//        val worlds = await(client.fetchAllWorlds())
//        println(s"worlds.size = ${worlds.size}")
//        assert(worlds.nonEmpty)
//      }

      "map floor [continentId = 1, floor = 1]" in {
        val mapFloor = await(client.fetchMapFloor(1, 1))
        val regions = mapFloor.regions
        assert(regions.size >= 6)
        val kryta = regions.find { t: (Int, WorldRegion) => t._2.name == "Kryta" }.get._2
        val lionsArch = kryta.maps.find { e: (Int, WorldMapFloorMap) => e._2.name == "Lion's Arch" }.get._2
        assert(lionsArch.pointsOfInterest.size > 0)
        assert(lionsArch.sectors.size > 0)
      }

      "misc" - {
        "build" in {
          val build = await(client.fetchBuild())
          assert(build.buildId >= 38057)
        }

        "colors" in {
          val colors = await(client.fetchColors())
          assert(colors.nonEmpty)
        }

        "files" in {
          val files = await(client.fetchFiles())
          assert(files !== null)
        }
      }

      "recipes" in {
        val recipes = Await.result(client.fetchAllRecipeDetails(), 60.seconds)
        assert(recipes.nonEmpty)
      }

      "items" in {
        val items = Await.result(client.fetchAllItemDetails(), 60.seconds)
        assert(items.nonEmpty)
      }
    }
  }
}
