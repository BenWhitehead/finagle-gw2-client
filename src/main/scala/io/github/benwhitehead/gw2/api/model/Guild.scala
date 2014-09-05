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
import java.util.UUID

/**
 * @author Ben Whitehead
 */
case class GuildDetails(
  @JsonProperty("guild_id") id: UUID,
  @JsonProperty("guild_name") name: String,
  @JsonProperty("tag") tag: String,
  @JsonProperty("emblem") emblem: Emblem
)
case class Emblem(
  @JsonProperty("background_id") backgroundId: Int,
  @JsonProperty("foreground_id") foregroundId: Int,
  @JsonProperty("flags") flags: List[Int],
  @JsonProperty("background_color_id") backgroundColorId: Int,
  @JsonProperty("foreground_primary_color_id") foregroundPrimaryColorId: Int,
  @JsonProperty("foreground_secondary_color_id") foregroundSecondaryColorId: Int
)
