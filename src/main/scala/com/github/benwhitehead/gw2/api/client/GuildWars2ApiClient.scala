package com.github.benwhitehead.gw2.api.client

import com.twitter.util.{Stopwatch, Await, Future}
import com.github.benwhitehead.gw2.api.model._

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
    case class Recipes(recipes: Seq[Int])
    client[Recipes](rf.getRecipes) flatMap {
      case r: Recipes =>
        fetchRecipes(r.recipes)
    }
  }

  def fetchAllItemDetails(): Future[Seq[Item]] = {
    println(s"fetchAllItemDetails()")
    case class Items(items: Seq[Int])
    client[Seq[Int]](rf.getItems) flatMap {
      case i: Items =>
        fetchItems(i.items filter { i => !badItemIds(i) })
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
    case class Events(events: Seq[Int])
    client[Seq[Event]](rf.getEventsForWorld(worldId))
  }
}

object GuildWars2ApiClient {
  def apply() = new GuildWars2ApiClient(
    GuildWars2ApiRestClient(),
    new GuildWars2ApiRequestFactory()
  )
}
