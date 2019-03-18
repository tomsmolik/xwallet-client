package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransactionInfoDetail(
    val address: String? = null,
    val amount: BigDecimal = BigDecimal.ZERO,
    val category: BitcoinCategoryType? = null,
    val fee: BigDecimal? = null,
    val vout: Long? = null
)
