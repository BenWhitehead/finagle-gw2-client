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

