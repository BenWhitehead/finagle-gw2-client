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

case class File(
  @JsonProperty("file_id") fileId: String,
  @JsonProperty("signature") signature: String
)

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
