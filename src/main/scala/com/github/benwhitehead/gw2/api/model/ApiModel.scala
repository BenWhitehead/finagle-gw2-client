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

/**
 * @author Ben Whitehead
 */
class Item(
  val description: String,
  val flags: List[String],
  val gameTypes: List[String],
  val iconFileId: Int,
  val iconFileSignature: String,
  val itemId: Int,
  val level: Int,
  val name: String,
  val rarity: String,
  val restrictions: List[String],
  val itemType: String,
  val vendorValue: Int,
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
  val upgradeComponent: ItemInfo,
  val weapon: ItemInfo
)

case class ItemInfo(
  damageType: String,
  defense: Int,
  suffix: String,
  suffixItemId: Int,
  `type`: String,
  weightClass: String,
  infusionSlots: List[InfusionSlot],
  infixUpgrade: InfixUpgrade,
  infusionUpgradeFlags: List[String],
  minPower: Int,
  maxPower: Int,
  noSellOrSort: Int,
  size: Int,
  bonuses: List[String],
  flags: List[String],
  colorId: Int,
  unlockType: String,
  durationMs: Int,
  description: String,
  recipeId: Int,
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

case class InfusionSlot(flags: List[String])

case class Buff(
  description: String,
  skillId: Int
)

case class Event(
  worldId: Int,
  mapId: Int,
  eventId: UUID,
  state: String
)

case class Recipe(
  disciplines: List[String],
  flags: List[String],
  ingredients: List[Ingredient],
  minRating: Int,
  outputItemCount: Int,
  outputItemId: Int,
  recipeId: Int,
  timeToCraftMs: Int,
  itemType: String
)

case class Ingredient(
  count: Int,
  itemId: Int
)

case class World(
  id: Int,
  name: String
)
