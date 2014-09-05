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
case class World(
  @JsonProperty("id") id: Int,
  @JsonProperty("name") name: String
)

case class MapName(
  @JsonProperty("id") id: Int,
  @JsonProperty("name") name: String
)

case class Continent(
  @JsonProperty("name") name: String,
  @JsonProperty("continent_dims") dimensions: List[Int],
  @JsonProperty("min_zoom") zoomMin: Int,
  @JsonProperty("max_zoom") zoomMax: Int,
  @JsonProperty("floors") floors: List[Int]
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
