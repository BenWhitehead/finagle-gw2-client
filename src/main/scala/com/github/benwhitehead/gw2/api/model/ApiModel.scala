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

package com.github.benwhitehead.gw2.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.{Date, UUID}

/**
 * @author Ben Whitehead
 */
class Item(
  @JsonProperty("description") val description: String,
  @JsonProperty("flags") val flags: List[String],
  @JsonProperty("game_types") val gameTypes: List[String],
  @JsonProperty("icon_file_id") val iconFileId: Int,
  @JsonProperty("icon_file_signature") val iconFileSignature: String,
  @JsonProperty("item_id") val id: Int,
  @JsonProperty("level") val level: Int,
  @JsonProperty("name") val name: String,
  @JsonProperty("rarity") val rarity: String,
  @JsonProperty("restrictions") val restrictions: List[String],
  @JsonProperty("type") val itemType: String,
  @JsonProperty("vendor_value") val vendorValue: Int,
  @JsonProperty("armor") val armor: ItemInfo,
  @JsonProperty("back") val back: ItemInfo,
  @JsonProperty("bag") val bag: ItemInfo,
  @JsonProperty("buff") val buff: ItemInfo,
  @JsonProperty("consumable") val consumable: ItemInfo,
  @JsonProperty("container") val container: ItemInfo,
  @JsonProperty("gathering") val gathering: ItemInfo,
  @JsonProperty("gizmo") val gizmo: ItemInfo,
  @JsonProperty("tool") val tool: ItemInfo,
  @JsonProperty("trinket") val trinket: ItemInfo,
  @JsonProperty("upgrade_component") val upgradeComponent: ItemInfo,
  @JsonProperty("weapon") val weapon: ItemInfo
)

case class ItemInfo(
  @JsonProperty("damage_type") damageType: String,
  @JsonProperty("defense") defense: Int,
  @JsonProperty("suffix") suffix: String,
  @JsonProperty("suffix_item_id") suffixItemId: Int,
  @JsonProperty("type") `type`: String,
  @JsonProperty("weight_class") weightClass: String,
  @JsonProperty("infusion_slots") infusionSlots: List[InfusionSlot],
  @JsonProperty("infix_upgrade") infixUpgrade: InfixUpgrade,
  @JsonProperty("infusion_upgrade_flags") infusionUpgradeFlags: List[String],
  @JsonProperty("min_power") minPower: Int,
  @JsonProperty("max_power") maxPower: Int,
  @JsonProperty("no_sell_or_sort") noSellOrSort: Int,
  @JsonProperty("size") size: Int,
  @JsonProperty("bonuses") bonuses: List[String],
  @JsonProperty("flags") flags: List[String],
  @JsonProperty("color_id") colorId: Int,
  @JsonProperty("unlock_type") unlockType: String,
  @JsonProperty("duration_ms") durationMs: Int,
  @JsonProperty("description") description: String,
  @JsonProperty("recipe_id") recipeId: Int,
  @JsonProperty("charges") charges: Int
)

case class InfixUpgrade(
  @JsonProperty("attributes") attributes: List[Attribute],
  @JsonProperty("buff") buff: Buff
)

case class Attribute(
  @JsonProperty("attribute") attribute: String,
  @JsonProperty("modifier") modifier: Int
)

case class InfusionSlot(
  @JsonProperty("flags") flags: List[String],
  @JsonProperty("item_id") itemId: Int
)

case class Buff(
  @JsonProperty("description") description: String,
  @JsonProperty("skill_id") skillId: Int
)

case class Event(
  @JsonProperty("world_id") worldId: Int,
  @JsonProperty("map_id") mapId: Int,
  @JsonProperty("event_id") eventId: UUID,
  @JsonProperty("state") state: String
)

case class EventDetails(
  @JsonProperty("name") name: String,
  @JsonProperty("level") level: Int,
  @JsonProperty("map_id") mapId: Int,
  @JsonProperty("flags") flags: List[String],
  @JsonProperty("location") location: Location
)

case class Location(
  @JsonProperty("type") shape: String,
  @JsonProperty("center") center: List[Double],
  @JsonProperty("height") height: Double,
  @JsonProperty("radius") radius: Double,
  @JsonProperty("rotation") rotation: Double,
  @JsonProperty("z_range") zRange: Double,
  @JsonProperty("points") points: List[List[Double]]
)

case class EventName(
  @JsonProperty("id") id: UUID,
  @JsonProperty("name")   name: String
)

case class MapName(
  @JsonProperty("id") id: Int,
  @JsonProperty("name")   name: String
)

case class Continent(
  @JsonProperty("name") name: String,
  @JsonProperty("continent_dims") dimensions: List[Int],
  @JsonProperty("min_zoom") zoomMin: Int,
  @JsonProperty("max_zoom") zoomMax: Int,
  @JsonProperty("floors") floors: List[Int]
)

case class Recipe(
  @JsonProperty("disciplines") disciplines: List[String],
  @JsonProperty("flags") flags: List[String],
  @JsonProperty("ingredients") ingredients: List[Ingredient],
  @JsonProperty("min_rating") minRating: Int,
  @JsonProperty("output_item_count") outputItemCount: Int,
  @JsonProperty("output_item_id") outputItemId: Int,
  @JsonProperty("recipe_id") recipeId: Int,
  @JsonProperty("time_to_craft_ms") timeToCraftMs: Int,
  @JsonProperty("type") itemType: String
)

case class Ingredient(
  @JsonProperty("count") count: Int,
  @JsonProperty("item_id") itemId: Int
)

case class World(
  @JsonProperty("id") id: Int,
  @JsonProperty("name") name: String
)

case class WorldMap(
  @JsonProperty("map_name") name: String,
  @JsonProperty("min_level") levelMin: Int,
  @JsonProperty("max_level") levelMax: Int,
  @JsonProperty("default_floor") defaultFloor: Int,
  @JsonProperty("floors") floors: List[Int],
  @JsonProperty("region_id") regionId: Int,
  @JsonProperty("region_name") regionName: Int,
  @JsonProperty("continent_id") continentId: Int,
  @JsonProperty("continent_name") continentName: Int,
  @JsonProperty("map_rect") rectangle: List[List[Int]],
  @JsonProperty("continent_rect") continentRectangle: List[List[Int]]
)

case class PointOfInterest(
  @JsonProperty("poi_id") id: Int,
  @JsonProperty("name") name: String,
  @JsonProperty("type")`type`: String,
  @JsonProperty("floor") floor: Int,
  @JsonProperty("coord") coordinate: List[Int]
)
case class Task(
  @JsonProperty("task_id") taskId: Int,
  @JsonProperty("objective") objective: String,
  @JsonProperty("level") level: Int,
  @JsonProperty("coord") coordinate: List[Int]
)
case class SkillChallenge(
  @JsonProperty("coord") coordinate: List[Int]
)
case class Sector(
  @JsonProperty("sector_id") sector_id: Int,
  @JsonProperty("name") name: String,
  @JsonProperty("level") level: Int,
  @JsonProperty("coord") coordinate: List[Int]
)

case class WorldMapFloor(
  @JsonProperty("texture_dims") textureDimensions: List[Int],
  @JsonProperty("regions") regions: Map[Int, WorldRegion]
)
case class WorldRegion(
  @JsonProperty("name") name: String,
  @JsonProperty("label_coord") labelCoordinate: List[Int],
  @JsonProperty("maps") maps: Map[Int, WorldMapFloorMap]
)
case class WorldMapFloorMap(
  @JsonProperty("name") name: String,
  @JsonProperty("min_level") levelMin: Int,
  @JsonProperty("max_level") levelMax: Int,
  @JsonProperty("default_floor") defaultFloor: Int,
  @JsonProperty("map_rect") rectangle: List[List[Int]],
  @JsonProperty("continent_rect") continentRectangle: List[List[Int]],

  @JsonProperty("points_of_interest") pointsOfInterest: List[PointOfInterest],
  @JsonProperty("tasks") tasks: List[Task],
  @JsonProperty("skill_challenges") skillChallenges: List[SkillChallenge],
  @JsonProperty("sectors") sectors: List[Sector]
)

case class GuildDetails(
  @JsonProperty("guild_id") id: UUID,
  @JsonProperty("guild_name") name: String,
  @JsonProperty("tag") tag: String,
  @JsonProperty("emblem") emblem: Emblem
)
case class Emblem(
  @JsonProperty("background_id") backgroundId: Int,
  @JsonProperty("foreground_id") foregroundId: Int,
  @JsonProperty("flags") flags: List[Int],
  @JsonProperty("background_color_id") backgroundColorId: Int,
  @JsonProperty("foreground_primary_color_id") foregroundPrimaryColorId: Int,
  @JsonProperty("foreground_secondary_color_id") foregroundSecondaryColorId: Int
)

case class WorldVsWorldMatch(
  @JsonProperty("wvw_match_id") matchId: String,
  @JsonProperty("red_world_id") redWorldId: Int,
  @JsonProperty("blue_world_id") blueWorldId: Int,
  @JsonProperty("green_world_id") greenWorldId: Int,
  @JsonProperty("start_time") startTime: Date,
  @JsonProperty("end_time") endTime: Date
)
case class WorldVsWorldMatchDetails(
  @JsonProperty("match_id") matchId: String,
  @JsonProperty("scores") scores: List[Int],
  @JsonProperty("maps") maps: List[WorldVsWorldMapDetails]
)
case class WorldVsWorldMapDetails(
  @JsonProperty ("type") `type`: String,
  @JsonProperty("scores") scores: List[Int],
  @JsonProperty("objectives") objectives: List[WorldVsWorldObjective],
  @JsonProperty("bonuses") bonuses: List[WorldVsWorldBonus]
)
case class WorldVsWorldObjective(
  @JsonProperty("id") id: Int,
  @JsonProperty("owner") owner: String,
  @JsonProperty("owner_guild") ownerGuildId: UUID
)
case class WorldVsWorldBonus(
  @JsonProperty("type") `type`: String,
  @JsonProperty("owner") owner: String
)
case class WorldVsWorldObjectName(
  @JsonProperty("id") id: Int,
  @JsonProperty("name") name: String
)

trait Unwrappable[U] {
  def flatMap: U
}
case class Items(items: Seq[Int]) extends Unwrappable[Seq[Int]] {
  def flatMap = items
}
case class Recipes(recipes: Seq[Int]) extends Unwrappable[Seq[Int]] {
  def flatMap = recipes
}
case class Events(events: Seq[Event]) extends Unwrappable[Seq[Event]] {
  def flatMap = events
}
case class Continents(continents: Map[Int, Continent]) extends Unwrappable[Map[Int, Continent]] {
  def flatMap = continents
}
case class WorldMaps(maps: Map[Int, WorldMap]) extends Unwrappable[Map[Int, WorldMap]] {
  def flatMap = maps
}
case class WorldVsWorldMatches(@JsonProperty("wvw_matches") matches: List[WorldVsWorldMatch]) extends Unwrappable[List[WorldVsWorldMatch]] {
  def flatMap = matches
}
