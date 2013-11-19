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

import com.twitter.bijection._
import com.twitter.bijection.json.JsonNodeInjection._
import com.twitter.bijection.json._
import java.util.UUID
import org.codehaus.jackson.JsonNode

/**
 * @author Ben Whitehead
 */
trait ApiModelJsonBijections extends CollectionBijections {

  implicit val mapTo = JsonInjection.toString[Map[String, JsonNode]]

  trait WorldJsonInjectors {
    implicit val worldJsonInjector: Injection[World, Map[String, JsonNode]] = Injection.build[World, Map[String, JsonNode]] { w =>
      Map(
        "id" -> toJsonNode[Int](w.id),
        "name" -> toJsonNode[String](w.name)
      )
    }
    { m => scala.util.Try {
      World(
        fromJsonNode[String](m("id")).get.toInt,
        fromJsonNode[String](m("name")).get
      )
    }
    }
    implicit val fullWorldJsonInjector: Injection[World, String] = Injection.connect[World, Map[String, JsonNode], String]
  }

  trait IngredientJsonInjectors {
    implicit val ingredientJsonInjector: Injection[Ingredient, Map[String, JsonNode]] = Injection.build[Ingredient, Map[String, JsonNode]] { i =>
      Map(
        "item_id" -> toJsonNode[Int](i.itemId),
        "count" -> toJsonNode[Int](i.count)
      )
    }
    { m => scala.util.Try {
      Ingredient(
        fromJsonNode[String](m("item_id")).get.toInt,
        fromJsonNode[String](m("count")).get.toInt
      )
    }
    }
    implicit val fullIngredientJsonInjector: Injection[Ingredient, String] = Injection.connect[Ingredient, Map[String, JsonNode], String]
  }

  trait BuffJsonInjectors {
    implicit val buffJsonInjector: Injection[Buff, Map[String, JsonNode]] = Injection.build[Buff, Map[String, JsonNode]] { s =>
      Map(
        "description" -> toJsonNode[String](s.description),
        "skill_id" -> toJsonNode[Int](s.skillId)
      )
    }
    { m => scala.util.Try {
      Buff(
        fromJsonNode[String](m("description")).get,
        fromJsonNode[String](m("skill_id")).get.toInt
      )
    }
    }
    implicit val fullBuffJsonInjector: Injection[Buff, String] = Injection.connect[Buff, Map[String, JsonNode], String]
  }

  trait AttributeJsonInjectors {
    implicit val attributeJsonInjector: Injection[Attribute, Map[String, JsonNode]] = Injection.build[Attribute, Map[String, JsonNode]] { a =>
      Map(
        "attribute" -> toJsonNode[String](a.attribute),
        "modifier" -> toJsonNode[Int](a.modifier)
      )
    }
    { m => scala.util.Try {
      Attribute(
        fromJsonNode[String](m("attribute")).get,
        fromJsonNode[String](m("modifier")).get.toInt
      )
    }
    }
    implicit val fullAttributeJsonInjector: Injection[Attribute, String] = Injection.connect[Attribute, Map[String, JsonNode], String]
  }
  
  trait InfixUpgradeJsonInjectors {
    implicit val infixUpgradeInjector: Injection[InfixUpgrade, Map[String, JsonNode]] = Injection.build[InfixUpgrade, Map[String, JsonNode]] { i =>
      Map(
        "attributes" -> toJsonNode[List[Attribute]](i.attributes),
        "buff" -> toJsonNode[Buff](i.buff)
      )
    }
    { m => scala.util.Try {
      InfixUpgrade(
        fromJsonNode[List[Attribute]](m("attributes")).get,
        fromJsonNode[Buff](m("buff")).get
      )
    }
    }
    implicit val fullInfixUpgradeJsonInjector: Injection[InfixUpgrade, String] = Injection.connect[InfixUpgrade, Map[String, JsonNode], String]
  }

  trait InfusionSlotJsonInjectors {
    implicit val infusionSlotInjector: Injection[InfusionSlot, Map[String, JsonNode]] = Injection.build[InfusionSlot, Map[String, JsonNode]] { i =>
      Map("flags" -> toJsonNode[List[String]](i.flags))
    }
    { m => scala.util.Try {
      InfusionSlot(fromJsonNode[List[String]](m("flags")).get)
    }
    }
    implicit val fullInfusionSlotInjector: Injection[InfusionSlot, String] = Injection.connect[InfusionSlot, Map[String, JsonNode], String]
  }

  trait EventJsonInjectors {
    implicit val eventInjector: Injection[Event, Map[String, JsonNode]] = Injection.build[Event, Map[String, JsonNode]] { e =>
      Map(
        "world_id" -> toJsonNode[Int](e.worldId),
        "map_id" -> toJsonNode[Int](e.mapId),
        "event_id" -> toJsonNode[String](e.eventId.toString),
        "state" -> toJsonNode[String](e.state)
      )
    }
    { m => scala.util.Try {
      Event(
        fromJsonNode[String](m("world_id")).get.toInt,
        fromJsonNode[String](m("map_id")).get.toInt,
        UUID.fromString(fromJsonNode[String](m("event_id")).get),
        fromJsonNode[String](m("state")).get
      )
    }
    }
    implicit val fullEventInjector: Injection[Event, String] = Injection.connect[Event, Map[String, JsonNode], String]
  }

  trait RecipeJsonInjectors {
    implicit val recipeJsonInjector: Injection[Recipe, Map[String, JsonNode]] = Injection.build[Recipe, Map[String, JsonNode]] { r =>
      Map(
        "disciplines" -> toJsonNode[List[String]](r.disciplines),
        "flags" -> toJsonNode[List[String]](r.flags),
        "ingredients" -> toJsonNode[List[Ingredient]](r.ingredients),
        "min_rating" -> toJsonNode[Int](r.minRating),
        "output_item_count" -> toJsonNode[Int](r.outputItemCount),
        "output_item_id" -> toJsonNode[Int](r.outputItemId),
        "recipe_id" -> toJsonNode[Int](r.recipeId),
        "time_to_craft_ms" -> toJsonNode[Int](r.timeToCraftMs),
        "type" -> toJsonNode[String](r.itemType)
      )
    }
    { m => scala.util.Try {
      Recipe(
        fromJsonNode[List[String]](m("disciplines")).get,
        fromJsonNode[List[String]](m("flags")).get,
        fromJsonNode[List[Ingredient]](m("ingredients")).get,
        //          List(),
        fromJsonNode[String](m("min_rating")).get.toInt,
        fromJsonNode[String](m("output_item_count")).get.toInt,
        fromJsonNode[String](m("output_item_id")).get.toInt,
        fromJsonNode[String](m("recipe_id")).get.toInt,
        fromJsonNode[String](m("time_to_craft_ms")).get.toInt,
        fromJsonNode[String](m("type")).get
      )
    }
    }
    implicit val fullRecipeJsonInjector: Injection[Recipe, String] = Injection.connect[Recipe, Map[String, JsonNode], String]
  }

  trait ItemInfoJsonInjectors {
    implicit val itemInfoInjector: Injection[ItemInfo, Map[String, JsonNode]] = Injection.build[ItemInfo, Map[String, JsonNode]] { i =>
      Map(
        "damage_type" -> toJsonNode[String](i.damageType),
        "defense" -> toJsonNode[Int](i.defense),
        "suffix" -> toJsonNode[String](i.suffix),
        "suffix_item_id" -> toJsonNode[Int](i.suffixItemId),
        "type" -> toJsonNode[String](i.`type`),
        "weight_class" -> toJsonNode[String](i.weightClass),
        "infusion_slots" -> toJsonNode[List[InfusionSlot]](i.infusionSlots),
        "infix_upgrade" -> toJsonNode[InfixUpgrade](i.infixUpgrade),
        "infusion_upgrade_flags" -> toJsonNode[List[String]](i.infusionUpgradeFlags),
        "min_power" -> toJsonNode[Int](i.minPower),
        "max_power" -> toJsonNode[Int](i.maxPower),
        "no_sell_or_sort" -> toJsonNode[Int](i.noSellOrSort),
        "size" -> toJsonNode[Int](i.size),
        "bonuses" -> toJsonNode[List[String]](i.bonuses),
        "flags" -> toJsonNode[List[String]](i.flags),
        "color_id" -> toJsonNode[Int](i.colorId),
        "unlock_type" -> toJsonNode[String](i.unlockType),
        "duration_ms" -> toJsonNode[Int](i.durationMs),
        "description" -> toJsonNode[String](i.description),
        "recipe_id" -> toJsonNode[Int](i.recipeId),
        "charges" -> toJsonNode[Int](i.charges)
      )
    }
    { m => scala.util.Try {
      ItemInfo(
        fromJsonNode[String](m("damage_type")).get,
        fromJsonNode[String](m("defense")).get.toInt,
        fromJsonNode[String](m("suffix")).get,
        fromJsonNode[String](m("suffix_item_id")).get.toInt,
        fromJsonNode[String](m("type")).get,
        fromJsonNode[String](m("weight_class")).get,
        fromJsonNode[List[InfusionSlot]](m("infusion_slots")).get,
        fromJsonNode[InfixUpgrade](m("infix_upgrade")).get,
        fromJsonNode[List[String]](m("infusion_upgrade_flags")).get,
        fromJsonNode[String](m("min_power")).get.toInt,
        fromJsonNode[String](m("max_power")).get.toInt,
        fromJsonNode[String](m("no_sell_or_sort")).get.toInt,
        fromJsonNode[String](m("size")).get.toInt,
        fromJsonNode[List[String]](m("bonuses")).get,
        fromJsonNode[List[String]](m("flags")).get,
        fromJsonNode[String](m("color_id")).get.toInt,
        fromJsonNode[String](m("unlock_type")).get,
        fromJsonNode[String](m("duration_ms")).get.toInt,
        fromJsonNode[String](m("description")).get,
        fromJsonNode[String](m("recipe_id")).get.toInt,
        fromJsonNode[String](m("charges")).get.toInt
      )
    }
    }
    implicit val fullItemInfoJsonInjector: Injection[ItemInfo, String] = Injection.connect[ItemInfo, Map[String, JsonNode], String]
  }

  trait ItemJsonInjectors {
    implicit val itemJsonInjector: Injection[Item, Map[String, JsonNode]] = Injection.build[Item, Map[String, JsonNode]] { i =>
      Map(
        "description" -> toJsonNode[String](i.description),
        "flags" -> toJsonNode[List[String]](i.flags),
        "game_types" -> toJsonNode[List[String]](i.gameTypes),
        "icon_file_id" -> toJsonNode[Int](i.iconFileId),
        "icon_file_signature" -> toJsonNode[String](i.iconFileSignature),
        "item_id" -> toJsonNode[Int](i.itemId),
        "level" -> toJsonNode[Int](i.level),
        "name" -> toJsonNode[String](i.name),
        "rarity" -> toJsonNode[String](i.rarity),
        "restrictions" -> toJsonNode[List[String]](i.restrictions),
        "type" -> toJsonNode[String](i.itemType),
        "vendor_value" -> toJsonNode[Int](i.vendorValue),
        "armor" -> toJsonNode[ItemInfo](i.armor),
        "back" -> toJsonNode[ItemInfo](i.back),
        "bag" -> toJsonNode[ItemInfo](i.bag),
        "buff" -> toJsonNode[ItemInfo](i.buff),
        "consumable" -> toJsonNode[ItemInfo](i.consumable),
        "container" -> toJsonNode[ItemInfo](i.container),
        "gathering" -> toJsonNode[ItemInfo](i.gathering),
        "gizmo" -> toJsonNode[ItemInfo](i.gizmo),
        "tool" -> toJsonNode[ItemInfo](i.tool),
        "trinket" -> toJsonNode[ItemInfo](i.trinket),
        "upgrade_component" -> toJsonNode[ItemInfo](i.upgradeComponent),
        "weapon" -> toJsonNode[ItemInfo](i.weapon)
      )
    }
    { m => scala.util.Try {
      new Item(
        fromJsonNode[String](m("description")).get,
        fromJsonNode[List[String]](m("flags")).get,
        fromJsonNode[List[String]](m("game_types")).get,
        fromJsonNode[String](m("icon_file_id")).get.toInt,
        fromJsonNode[String](m("icon_file_signature")).get,
        fromJsonNode[String](m("item_id")).get.toInt,
        fromJsonNode[String](m("level")).get.toInt,
        fromJsonNode[String](m("name")).get,
        fromJsonNode[String](m("rarity")).get,
        fromJsonNode[List[String]](m("restrictions")).get,
        fromJsonNode[String](m("type")).get,
        fromJsonNode[String](m("vendor_value")).get.toInt,
        fromJsonNode[ItemInfo](m("armor")).get,
        fromJsonNode[ItemInfo](m("back")).get,
        fromJsonNode[ItemInfo](m("bag")).get,
        fromJsonNode[ItemInfo](m("buff")).get,
        fromJsonNode[ItemInfo](m("consumable")).get,
        fromJsonNode[ItemInfo](m("container")).get,
        fromJsonNode[ItemInfo](m("gathering")).get,
        fromJsonNode[ItemInfo](m("gizmo")).get,
        fromJsonNode[ItemInfo](m("tool")).get,
        fromJsonNode[ItemInfo](m("trinket")).get,
        fromJsonNode[ItemInfo](m("upgrade_component")).get,
        fromJsonNode[ItemInfo](m("weapon")).get
      )
    }
    }
    implicit val fullItemJsonInjector: Injection[Item, String] = Injection.connect[Item, Map[String, JsonNode], String]
  }
}
