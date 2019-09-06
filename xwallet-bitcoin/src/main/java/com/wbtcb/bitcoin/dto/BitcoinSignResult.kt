package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinSignResult(
    @JsonProperty("hex")
    val txId: String,

    @JsonProperty("complete")
    var complete: Boolean
)
