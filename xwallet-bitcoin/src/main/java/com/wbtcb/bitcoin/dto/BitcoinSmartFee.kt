package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinSmartFee(
    val feerate: BigDecimal = BigDecimal.ZERO,
    val blocks: Long = 0
)
