package io.github.benwhitehead.gw2.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Ben Whitehead
 */
case class Listing(
  @JsonProperty("id") id: Int,
  @JsonProperty("buys") buys: Seq[ListingEntry],
  @JsonProperty("sells") sells: Seq[ListingEntry]
)

case class ListingEntry(
  @JsonProperty("listings") numListings: Int,
  @JsonProperty("unit_price") unitPrice: Int,
  @JsonProperty("quantity") quantity: Int
)
