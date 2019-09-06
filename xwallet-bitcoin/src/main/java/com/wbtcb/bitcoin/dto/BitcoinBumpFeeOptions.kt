package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinBumpFeeOptions(

    @JsonProperty("confTarget")
    var confTarget: BigDecimal = 1.toBigDecimal(),

    @JsonProperty("totalFee")
    var totalFee: BigDecimal? = null,

    @JsonProperty("estimate_mode")
    var estimate_mode: String = "CONSERVATIVE"
)
