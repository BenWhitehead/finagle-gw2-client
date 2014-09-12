package io.github.benwhitehead.gw2.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Ben Whitehead
 */
case class Quaggan(
  @JsonProperty("id") id: String,
  @JsonProperty("url") url: String
)
