package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransactionOutput(
    val value: BigDecimal? = null,
    val n: Long? = null,
    val scriptPubKey: BitcoinScriptPubKey? = null
)
