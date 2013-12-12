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
import com.twitter.util.Future

/**
 * @author Ben Whitehead
 */
class GuildWars2ApiClient(client: GuildWars2ApiRestClient, rf: GuildWars2ApiRequestFactory) {

  /**
   * These cause 500's
   */
  val badItemIds = Set(43949, 43948)

  def fetchAllRecipeDetails(): Future[Seq[Recipe]] = {
    println(s"fetchAllRecipeDetails()")
    client[Recipes](rf.getRecipes) flatMap {
      case r: Recipes => fetchRecipes(r.recipes)
    }
  }

  def fetchItemIds(): Future[Seq[Int]] = {
    println(s"fetchItemIds()")
    client[Items](rf.getItems) flatMap {
      case i: Items => Future.value(i.items filter { i => !badItemIds(i) })
    }
  }

  def fetchAllItemDetails(): Future[Seq[Item]] = {
    println("fetchAllItemDetails()")
    fetchItemIds() flatMap {
      case itemIds: Seq[Int] => fetchItems(itemIds)
    }
  }

  def fetchRecipes(recipeIds: Seq[Int]): Future[Seq[Recipe]] = {
    println(s"fetchRecipes(recipeIds = $recipeIds)")
    Future.collect(
      recipeIds map {
        id => fetchRecipeDetails(id)
      }
    )
  }

  def fetchRecipeDetails(recipeId: Int): Future[Recipe] = {
    println(s"fetchRecipeDetails(recipeId = $recipeId)")
    client[Recipe](rf.getRecipeDetails(recipeId))
  }

  def fetchItems(itemIds: Seq[Int]): Future[Seq[Item]] = {
    println(s"fetchItems(itemIds = $itemIds)")
    Future.collect(
      itemIds map {
        id => fetchItemDetails(id)
      }
    )
  }

  def fetchItemDetails(itemId: Int): Future[Item] = {
    println(s"fetchItemDetails(itemId = $itemId)")
    client[Item](rf.getItemDetails(itemId))
  }

  def fetchAllWorlds(): Future[Seq[World]] = {
    println(s"fetchAllWorlds()")
    client[Seq[World]](rf.getWorldNames)
  }

  def fetchAllEvents(worldId: Int): Future[Seq[Event]] = {
    println(s"fetchAllEvents(worldId = $worldId)")
    client[Events](rf.getEventsForWorld(worldId)) flatMap {
      case e: Events =>
        Future.value(e.events)
    }
  }
}

object GuildWars2ApiClient {
  def apply() = new GuildWars2ApiClient(
    GuildWars2ApiRestClient(),
    new GuildWars2ApiRequestFactory()
  )
}
