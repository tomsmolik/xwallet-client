package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinAddress(

    @JsonProperty("address")
    val address: String,

    @JsonProperty("amount")
    val amount: BigDecimal,

    @JsonProperty("label")
    val label: String?
)
