package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransactionInfo(
    val amount: BigDecimal = BigDecimal.ZERO,
    val fee: BigDecimal? = BigDecimal.ZERO,
    val confirmations: Long = 0,
    val blockhash: String? = null,
    val blockindex: Long = 0,
    val blocktime: Date? = null,
    val txid: String = "",
    val time: Date? = null,
    val timereceived: Date? = null,
    val comment: String? = null,
    val walletconflicts: List<String>? = null,
    val details: List<BitcoinTransactionInfoDetail>? = null
)
