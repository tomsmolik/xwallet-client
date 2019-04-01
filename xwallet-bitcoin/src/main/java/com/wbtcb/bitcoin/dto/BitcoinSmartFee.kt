package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinSmartFee(

    @JsonProperty("feerate")
    val feeRate: BigDecimal,

    @JsonProperty("blocks")
    val blocks: Long
)
