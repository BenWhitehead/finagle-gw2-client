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
import com.twitter.util.Future
import java.util.UUID

/**
 * @author Ben Whitehead
 */
class GuildWars2ApiClient(client: GuildWars2ApiRestClient, rf: GuildWars2ApiRequestFactory) {

  // -------------- Recipes ---------------------------------------------------

  def fetchRecipeIds(): Future[Seq[Int]] = {
    unwrap(client[Recipes](rf.getRecipes))
  }
  def fetchAllRecipeDetails(): Future[Seq[Recipe]] = {
    fetchRecipeIds() flatMap {
      case recipeIds: Seq[Int] => fetchRecipeDetails(recipeIds)
    }
  }

  def fetchRecipeDetails(recipeIds: Seq[Int]): Future[Seq[Recipe]] = {
    Future.collect(
      recipeIds map {
        id => fetchRecipeDetails(id)
      }
    )
  }

  def fetchRecipeDetails(recipeId: Int): Future[Recipe] = {
    client[Recipe](rf.getRecipeDetails(recipeId))
  }

  // -------------- Items -----------------------------------------------------

  def fetchItemIds()(implicit badItemIds: Set[Int] = Set(43949, 43948)): Future[Seq[Int]] = {
    unwrap(client[Items](rf.getItems)) flatMap {
      case ids: Seq[Int] => Future.value(ids filter { i => !badItemIds(i) })
    }
  }

  def fetchAllItemDetails(): Future[Seq[Item]] = {
    fetchItemIds() flatMap {
      case itemIds: Seq[Int] => fetchItemDetails(itemIds)
    }
  }

  def fetchItemDetails(itemIds: Seq[Int]): Future[Seq[Item]] = {
    Future.collect(
      itemIds map {
        id => fetchItemDetails(id)
      }
    )
  }

  def fetchItemDetails(itemId: Int): Future[Item] = {
    client[Item](rf.getItemDetails(itemId))
  }

  // -------------- Misc ------------------------------------------------------

  def fetchBuild(): Future[Build] = {
    client[Build](rf.getBuild)
  }

  def fetchColors(): Future[Map[String, ColorDefinition]] = {
    unwrap(client[Colors](rf.getColors))
  }

  def fetchFiles(): Future[Map[String, File]] = {
    client[Map[String, File]](rf.getFiles)
  }

  // -------------- Worlds ----------------------------------------------------

  def fetchAllWorlds(): Future[Seq[World]] = {
    client[Seq[World]](rf.getWorldNames)
  }

  // -------------- Maps ------------------------------------------------------

  def fetchMapNames(): Future[Seq[MapName]] = {
    client[Seq[MapName]](rf.getMapNames)
  }

  def fetchContinents(): Future[Map[Int, Continent]] = {
    unwrap(client[Continents](rf.getContinents))
  }

  def fetchMaps(): Future[Map[Int, WorldMap]] = {
    unwrap(client[WorldMaps](rf.getMaps))
  }
  def fetchMaps(mapId: Int): Future[Map[Int, WorldMap]] = {
    unwrap(client[WorldMaps](rf.getMaps(mapId)))
  }

  def fetchMapFloor(continentId: Int, floor: Int): Future[WorldMapFloor] = {
    client[WorldMapFloor](rf.getMapFloor(continentId, floor))
  }

  // -------------- Events ----------------------------------------------------

  def fetchEventNames(): Future[Seq[EventName]] = {
    client[Seq[EventName]](rf.getEventNames)
  }

  def fetchEventDetails(): Future[Map[UUID, EventDetails]] = {
    client[Map[UUID, EventDetails]](rf.getEventDetails)
  }
  def fetchEventDetails(eventId: UUID): Future[Map[UUID, EventDetails]] = {
    client[Map[UUID, EventDetails]](rf.getEventDetails(eventId))
  }

  def fetchEvents(): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEvents))
  }
  def fetchEvents(eventId: UUID): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEvents(eventId)))
  }
  def fetchEvents(eventId: UUID, worldId: Int, mapId: Int): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEvents(eventId, worldId, mapId)))
  }
  def fetchEvents(worldId: Int, mapId: Int): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEvents(worldId, mapId)))
  }

  def fetchEventsForWorld(worldId: Int): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEventsForWorld(worldId)))
  }
  def fetchEventsForMap(mapId: Int): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEventsForMap(mapId)))
  }
  def fetchEventsForWorld(eventId: UUID, worldId: Int): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEventsForWorld(eventId, worldId)))
  }
  def fetchEventsForMap(eventId: UUID, mapId: Int): Future[Seq[Event]] = {
    unwrap(client[Events](rf.getEventsForMap(eventId, mapId)))
  }

  // -------------- Guilds ----------------------------------------------------

  def fetchGuildDetails(guildName: String): Future[GuildDetails] = {
    client[GuildDetails](rf.getGuildDetails(guildName))
  }
  def fetchGuildDetails(guildId: UUID): Future[GuildDetails] = {
    client[GuildDetails](rf.getGuildDetails(guildId))
  }

  // -------------- WvW -------------------------------------------------------

  def fetchWorldVsWorldMatches(): Future[Seq[WorldVsWorldMatch]] = {
    unwrap(client[WorldVsWorldMatches](rf.getWvwMatches))
  }

  def fetchWorldVsWorldMatchDetails(matchId: String): Future[WorldVsWorldMatchDetails] = {
    client[WorldVsWorldMatchDetails](rf.getWvwMatchDetails(matchId))
  }

  def fetchWorldVsWorldObjectNames(): Future[Seq[WorldVsWorldObjectName]] = {
    client[Seq[WorldVsWorldObjectName]](rf.getWvwObjectiveNames)
  }

  // -------------- Quaggans --------------------------------------------------

  def fetchQuagganIds(): Future[Seq[String]] = {
    client[Seq[String]](rf.getQuagganIds)
  }

  def fetchQuaggans(ids: Seq[String]) = {
    Future.collect(
      ids map { id => fetchQuaggan(id) }
    )
  }

  def fetchQuaggan(id: String): Future[Quaggan] = {
    client[Quaggan](rf.getQuaggan(id))
  }

  def fetchAllQuaggans(): Future[Seq[Quaggan]] = {
    fetchQuagganIds() flatMap {
      case quagganIds: Seq[String] => fetchQuaggans(quagganIds)
    }
  }


  private def unwrap[U](f: Future[Unwrappable[U]]): Future[U] = f flatMap {
    case u: Unwrappable[U] => Future.value(u.flatMap)
  }
}

object GuildWars2ApiClient {
  def apply() = new GuildWars2ApiClient(
    GuildWars2ApiRestClient(),
    new GuildWars2ApiRequestFactory()
  )
}
