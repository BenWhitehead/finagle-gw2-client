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

package com.github.benwhitehead.gw2.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Ben Whitehead
 */
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
