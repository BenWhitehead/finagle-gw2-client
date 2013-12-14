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

import com.github.theon.uri.Uri
import com.github.theon.uri.Uri._
import com.twitter.finagle.http.RequestBuilder
import java.util.UUID

/**
 * @see http://wiki.guildwars2.com/wiki/API
 * @author Ben Whitehead
 */
class GuildWars2ApiRequestFactory(baseUri: String = "https://api.guildwars2.com:443/v1") {

  implicit val defaultLang = _lang("en")

  private[this] def get(uri: Uri)(implicit lang: (String, Any)) = {
    uri.param(lang)
    RequestBuilder().url(s"$baseUri/${uri.toString()}".replaceAll("(?<!:)//", "/")).buildGet
  }
  def _lang(lang: String): (String, Any) = "lang" -> lang

  def getBuild                                              = get("/build.json")
  def getColors(lang: String = "en")                        = get("/colors.json")
  def getFiles                                              = get("/files.json")
  def getWorldNames                                         = get("/world_names.json")

  /* ----- Maps ----- */
  def getMapNames                                           = get("/map_names.json")

  def getContinents                                         = get("/continents.json")
  
  def getMaps                                               = get("/maps.json")
  def getMaps(mapId: Int)                                   = get("/maps.json" ? ("map_id" -> mapId))
  
  def getMapFloor(continentId: Int, floor: Int)             = get("/map_floor.json" ? ("continent_id" -> continentId) & "floor" -> floor)

  /* ----- Items ----- */
  def getItems                                              = get("/items.json")
  def getItemDetails(itemId: Int)                           = get("/item_details.json" ? ("item_id" -> itemId))

  def getRecipes                                            = get("/recipes.json")
  def getRecipeDetails(recipeId: Int)                       = get("/recipe_details.json" ? ("recipe_id" -> recipeId))

  /* ----- Events ----- */
  def getEventNames                                         = get("/event_names.json")

  def getEventDetails                                       = get("/event_details.json")
  def getEventDetails(eventId: UUID)                        = get("/event_details.json" ? ("event_id" -> eventId))

  def getEvents                                             = get("/events.json")
  def getEvents(eventId: UUID)                              = get("/events.json" ? ("event_id" -> eventId))
  def getEvents(eventId: UUID, worldId: Int, mapId: Int)    = get("/events.json" ? ("event_id" -> eventId) & ("world_id" -> worldId) & ("map_id" -> mapId))
  def getEvents(worldId: Int, mapId: Int)                   = get("/events.json" ? ("world_id" -> worldId) & ("map_id" -> mapId))

  def getEventsForWorld(worldId: Int)                       = get("/events.json" ? ("world_id" -> worldId))
  def getEventsForMap(mapId: Int)                           = get("/events.json" ? ("map_id" -> mapId))
  def getEventsForWorld(eventId: UUID, worldId: Int)        = get("/events.json" ? ("event_id" -> eventId) & ("world_id" -> worldId))
  def getEventsForMap(eventId: UUID, mapId: Int)            = get("/events.json" ? ("event_id" -> eventId) & ("map_id" -> mapId))

  /* ----- Guilds ----- */
  def getGuildDetails(guildId: UUID)                        = get("/guild_details.json" ? ("guild_id" -> guildId))
  def getGuildDetails(guildName: String)                    = get("/guild_details.json" ? ("guild_name" -> guildName))

}
