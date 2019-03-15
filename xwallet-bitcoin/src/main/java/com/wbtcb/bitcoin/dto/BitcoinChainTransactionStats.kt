package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinChainTransactionStats(
    val time: Long? = null,
    val txcount: Long? = null,
    val txrate: BigDecimal? = null
)
