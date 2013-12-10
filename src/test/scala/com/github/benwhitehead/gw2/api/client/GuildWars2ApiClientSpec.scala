package com.github.benwhitehead.gw2.api.client

import org.scalatest.FreeSpec
import com.twitter.util.{Await, Future, Stopwatch}

/**
 * @author Ben Whitehead
 */
class GuildWars2ApiClientSpec extends FreeSpec {
  "Guild Wars 2 Api Client should" - {
    val client = new GuildWars2ApiClient(
      GuildWars2ApiRestClient(hostConnectionLimit = 30),
      new GuildWars2ApiRequestFactory()
    )

    "script" in {
      val time = Stopwatch.start()
      val f = Future.join(
        client.fetchAllRecipeDetails(),
        client.fetchAllItemDetails(),
        client.fetchAllWorlds(),
        client.fetchAllEvents(1015)
      ) flatMap {
        case (recipes, items, worlds, events) =>
          Future.value((recipes, items, worlds, events))
      } onSuccess {
        case (recipes, items, worlds, events) =>
          println(s"recipes.size = ${recipes.size}")
          println(s"items.size = ${items.size}")
          println(s"worlds.size = ${worlds.size}")
          println(s"events.size = ${events.size}")
          println(time())
      }
      Await.all(f)

      System.exit(0)
    }
  }
}
