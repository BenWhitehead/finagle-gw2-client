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

import com.netaporter.uri.Uri
import com.netaporter.uri.dsl._
import com.twitter.finagle.http.RequestBuilder
import java.util.UUID

/**
 * @see http://wiki.guildwars2.com/wiki/API
 * @author Ben Whitehead
 */
class GuildWars2ApiRequestFactory(baseUri: String = "https://api.guildwars2.com:443/") {

  implicit val defaultLang = _lang("en")

  private[this] def get(uri: Uri)(implicit lang: (String, Any)) = {
    uri.addParam(lang._1, lang._2)
    RequestBuilder().url(s"$baseUri/${uri.toString()}".replaceAll("(?<!:)//", "/")).buildGet
  }
  def _lang(lang: String): (String, Any) = "lang" -> lang

  def getBuild                                              = get("/v1/build.json")
  def getColors                                             = get("/v1/colors.json")
  def getFiles                                              = get("/v1/files.json")
  def getWorldNames                                         = get("/v1/world_names.json")

  /* ----- Maps ----- */
  def getMapNames                                           = get("/v1/map_names.json")

  def getContinents                                         = get("/v1/continents.json")
  
  def getMaps                                               = get("/v1/maps.json")
  def getMaps(mapId: Int)                                   = get("/v1/maps.json" ? ("map_id" -> mapId))
  
  def getMapFloor(continentId: Int, floor: Int)             = get("/v1/map_floor.json" ? ("continent_id" -> continentId) & "floor" -> floor)

  /* ----- Items ----- */
  def getItems                                              = get("/v1/items.json")
  def getItemDetails(itemId: Int)                           = get("/v1/item_details.json" ? ("item_id" -> itemId))

  def getRecipes                                            = get("/v1/recipes.json")
  def getRecipeDetails(recipeId: Int)                       = get("/v1/recipe_details.json" ? ("recipe_id" -> recipeId))

  /* ----- Events ----- */
  def getEventNames                                         = get("/v1/event_names.json")

  def getEventDetails                                       = get("/v1/event_details.json")
  def getEventDetails(eventId: UUID)                        = get("/v1/event_details.json" ? ("event_id" -> eventId))

  def getEvents                                             = get("/v1/events.json")
  def getEvents(eventId: UUID)                              = get("/v1/events.json" ? ("event_id" -> eventId))
  def getEvents(eventId: UUID, worldId: Int, mapId: Int)    = get("/v1/events.json" ? ("event_id" -> eventId) & ("world_id" -> worldId) & ("map_id" -> mapId))
  def getEvents(worldId: Int, mapId: Int)                   = get("/v1/events.json" ? ("world_id" -> worldId) & ("map_id" -> mapId))

  def getEventsForWorld(worldId: Int)                       = get("/v1/events.json" ? ("world_id" -> worldId))
  def getEventsForMap(mapId: Int)                           = get("/v1/events.json" ? ("map_id" -> mapId))
  def getEventsForWorld(eventId: UUID, worldId: Int)        = get("/v1/events.json" ? ("event_id" -> eventId) & ("world_id" -> worldId))
  def getEventsForMap(eventId: UUID, mapId: Int)            = get("/v1/events.json" ? ("event_id" -> eventId) & ("map_id" -> mapId))

  /* ----- Guilds ----- */
  def getGuildDetails(guildId: UUID)                        = get("/v1/guild_details.json" ? ("guild_id" -> guildId))
  def getGuildDetails(guildName: String)                    = get("/v1/guild_details.json" ? ("guild_name" -> guildName))

  /* ----- WvW ----- */
  def getWvwMatches                                         = get("/v1/wvw/matches.json")
  def getWvwMatchDetails(matchId: String)                   = get("/v1/wvw/match_details.json" ? ("match_id" -> matchId))
  def getWvwObjectiveNames                                  = get("/v1/wvw/objective_names.json")

}
