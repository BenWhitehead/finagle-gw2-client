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
import java.util.{UUID, Date}

/**
 * @author Ben Whitehead
 */
case class WorldVsWorldMatch(
  @JsonProperty("wvw_match_id") matchId: String,
  @JsonProperty("red_world_id") redWorldId: Int,
  @JsonProperty("blue_world_id") blueWorldId: Int,
  @JsonProperty("green_world_id") greenWorldId: Int,
  @JsonProperty("start_time") startTime: Date,
  @JsonProperty("end_time") endTime: Date
)
case class WorldVsWorldMatchDetails(
  @JsonProperty("match_id") matchId: String,
  @JsonProperty("scores") scores: List[Int],
  @JsonProperty("maps") maps: List[WorldVsWorldMapDetails]
)
case class WorldVsWorldMapDetails(
  @JsonProperty ("type") `type`: String,
  @JsonProperty("scores") scores: List[Int],
  @JsonProperty("objectives") objectives: List[WorldVsWorldObjective],
  @JsonProperty("bonuses") bonuses: List[WorldVsWorldBonus]
)
case class WorldVsWorldObjective(
  @JsonProperty("id") id: Int,
  @JsonProperty("owner") owner: String,
  @JsonProperty("owner_guild") ownerGuildId: UUID
)
case class WorldVsWorldBonus(
  @JsonProperty("type") `type`: String,
  @JsonProperty("owner") owner: String
)
case class WorldVsWorldObjectName(
  @JsonProperty("id") id: Int,
  @JsonProperty("name") name: String
)
