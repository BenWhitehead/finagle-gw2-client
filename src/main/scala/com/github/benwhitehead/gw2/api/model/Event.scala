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
import java.util.UUID

/**
 * @author Ben Whitehead
 */
case class EventName(
  @JsonProperty("id") id: UUID,
  @JsonProperty("name")   name: String
)

case class Event(
  @JsonProperty("world_id") worldId: Int,
  @JsonProperty("map_id") mapId: Int,
  @JsonProperty("event_id") eventId: UUID,
  @JsonProperty("state") state: String
)

case class EventDetails(
  @JsonProperty("name") name: String,
  @JsonProperty("level") level: Int,
  @JsonProperty("map_id") mapId: Int,
  @JsonProperty("flags") flags: List[String],
  @JsonProperty("location") location: Location
)

case class Location(
  @JsonProperty("type") shape: String,
  @JsonProperty("center") center: List[Double],
  @JsonProperty("height") height: Double,
  @JsonProperty("radius") radius: Double,
  @JsonProperty("rotation") rotation: Double,
  @JsonProperty("z_range") zRange: Double,
  @JsonProperty("points") points: List[List[Double]]
)
