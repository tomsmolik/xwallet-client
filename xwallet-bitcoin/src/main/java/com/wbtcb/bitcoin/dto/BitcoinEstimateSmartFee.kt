package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinEstimateSmartFee(
    val feerate: BigDecimal? = null,
    val errors: List<String>? = null,
    val blocks: Long? = null
)
