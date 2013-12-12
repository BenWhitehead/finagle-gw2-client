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

import java.util.UUID
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Ben Whitehead
 */
class Item(
  val description: String,
  val flags: List[String],
  @JsonProperty("game_types") val gameTypes: List[String],
  @JsonProperty("icon_file_id") val iconFileId: Int,
  @JsonProperty("icon_file_signature") val iconFileSignature: String,
  @JsonProperty("item_id") val itemId: Int,
  val level: Int,
  val name: String,
  val rarity: String,
  val restrictions: List[String],
  @JsonProperty("type") val itemType: String,
  @JsonProperty("vendor_value") val vendorValue: Int,
  val armor: ItemInfo,
  val back: ItemInfo,
  val bag: ItemInfo,
  val buff: ItemInfo,
  val consumable: ItemInfo,
  val container: ItemInfo,
  val gathering: ItemInfo,
  val gizmo: ItemInfo,
  val tool: ItemInfo,
  val trinket: ItemInfo,
  @JsonProperty("upgrade_component") val upgradeComponent: ItemInfo,
  val weapon: ItemInfo
)

case class ItemInfo(
  @JsonProperty("damage_type") damageType: String,
  defense: Int,
  suffix: String,
  @JsonProperty("suffix_item_id") suffixItemId: Int,
  @JsonProperty("type") `type`: String,
  @JsonProperty("weight_class") weightClass: String,
  @JsonProperty("infusion_slots") infusionSlots: List[InfusionSlot],
  @JsonProperty("infix_upgrade") infixUpgrade: InfixUpgrade,
  @JsonProperty("infusion_upgrade_flags") infusionUpgradeFlags: List[String],
  @JsonProperty("min_power") minPower: Int,
  @JsonProperty("max_power") maxPower: Int,
  @JsonProperty("no_sell_or_sort") noSellOrSort: Int,
  size: Int,
  bonuses: List[String],
  flags: List[String],
  @JsonProperty("color_id") colorId: Int,
  @JsonProperty("unlock_type") unlockType: String,
  @JsonProperty("duration_ms") durationMs: Int,
  description: String,
  @JsonProperty("recipe_id") recipeId: Int,
  charges: Int
)

case class InfixUpgrade(
  attributes: List[Attribute],
  buff: Buff
)

case class Attribute(
  attribute: String,
  modifier: Int
)

case class InfusionSlot(flags: List[String], @JsonProperty("item_id") itemId: Int)

case class Buff(
  description: String,
  @JsonProperty("skill_id") skillId: Int
)

case class Event(
  @JsonProperty("world_id") worldId: Int,
  @JsonProperty("map_id") mapId: Int,
  @JsonProperty("event_id") eventId: UUID,
  state: String
)

case class Recipe(
  disciplines: List[String],
  flags: List[String],
  ingredients: List[Ingredient],
  @JsonProperty("min_rating") minRating: Int,
  @JsonProperty("output_item_count") outputItemCount: Int,
  @JsonProperty("output_item_id") outputItemId: Int,
  @JsonProperty("recipe_id") recipeId: Int,
  @JsonProperty("time_to_craft_ms") timeToCraftMs: Int,
  @JsonProperty("type") itemType: String
)

case class Ingredient(
  count: Int,
  @JsonProperty("item_id") itemId: Int
)

case class World(
  id: Int,
  name: String
)

case class Items(items: Seq[Int])
case class Recipes(recipes: Seq[Int])
case class Events(events: Seq[Event])
