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
package io.github.benwhitehead.gw2.api.model

import com.fasterxml.jackson.annotation.JsonProperty


case class Build(
  @JsonProperty("build_id") buildId: Int
)

class Files(
  @JsonProperty("map_complete") val mapComplete: File,
  @JsonProperty("map_dungeon") val mapDungeon: File,
  @JsonProperty("map_heart_empty") val mapHeartEmpty: File,
  @JsonProperty("map_heart_full") val mapHeartFull: File,
  @JsonProperty("map_node_harvesting") val mapNodeHarvesting: File,
  @JsonProperty("map_node_logging") val mapNodeLogging: File,
  @JsonProperty("map_node_mining") val mapNodeMining: File,
  @JsonProperty("map_poi") val mapPoi: File,
  @JsonProperty("map_special_event") val mapSpecialEvent: File,
  @JsonProperty("map_story") val mapStory: File,
  @JsonProperty("map_waypoint") val mapWaypoint: File,
  @JsonProperty("map_waypoint_contested") val mapWaypointContested: File,
  @JsonProperty("map_waypoint_hover") val mapWaypointHover: File,
  @JsonProperty("wvw_battles_hollow_blue") val wvwBattlesHollowBlue: File,
  @JsonProperty("wvw_battles_hollow_green") val wvwBattlesHollowGreen: File,
  @JsonProperty("wvw_battles_hollow_red") val wvwBattlesHollowRed: File,
  @JsonProperty("wvw_battles_hollow_white") val wvwBattlesHollowWhite: File,
  @JsonProperty("wvw_bauers_estate_blue") val wvwBauersEstateBlue: File,
  @JsonProperty("wvw_bauers_estate_green") val wvwBauersEstateGreen: File,
  @JsonProperty("wvw_bauers_estate_red") val wvwBauersEstateRed: File,
  @JsonProperty("wvw_bauers_estate_white") val wvwBauersEstateWhite: File,
  @JsonProperty("wvw_carvers_ascent_blue") val wvwCarversAscentBlue: File,
  @JsonProperty("wvw_carvers_ascent_green") val wvwCarversAscentGreen: File,
  @JsonProperty("wvw_carvers_ascent_red") val wvwCarversAscentRed: File,
  @JsonProperty("wvw_carvers_ascent_white") val wvwCarversAscentWhite: File,
  @JsonProperty("wvw_orchard_overlook_blue") val wvwOrchardOverlookBlue: File,
  @JsonProperty("wvw_orchard_overlook_green") val wvwOrchardOverlookGreen: File,
  @JsonProperty("wvw_orchard_overlook_red") val wvwOrchardOverlookRed: File,
  @JsonProperty("wvw_orchard_overlook_white") val wvwOrchardOverlookWhite: File,
  @JsonProperty("wvw_temple_of_lost_prayers_blue") val wvwTempleOfLostPrayersBlue: File,
  @JsonProperty("wvw_temple_of_lost_prayers_green") val wvwTempleOfLostPrayersGreen: File,
  @JsonProperty("wvw_temple_of_lost_prayers_red") val wvwTempleOfLostPrayersRed: File,
  @JsonProperty("wvw_temple_of_lost_prayers_white") val wvwTempleOfLostPrayersWhite: File
)
case class File(
  @JsonProperty("file_id") fileId: String,
  @JsonProperty("signature") signature: String
)

case class Colors(@JsonProperty("colors") colors: Map[String, ColorDefinition]) extends Unwrappable[Map[String, ColorDefinition]] {
  def flatMap = colors
}
case class ColorDefinition(
  @JsonProperty("name") name: String,
  @JsonProperty("base_rgb") baseRgb: List[Int],
  @JsonProperty("cloth") cloth: MaterialColorDefinition,
  @JsonProperty("leather") leather: MaterialColorDefinition,
  @JsonProperty("metal") metal: MaterialColorDefinition
)
case class MaterialColorDefinition(
  @JsonProperty("rgb") rgb: List[Int],
  @JsonProperty("hue") hue: Double,
  @JsonProperty("saturation") saturation: Double,
  @JsonProperty("brightness") brightness: Double,
  @JsonProperty("contrast") contrast: Double,
  @JsonProperty("lightness") lightness: Double
)
