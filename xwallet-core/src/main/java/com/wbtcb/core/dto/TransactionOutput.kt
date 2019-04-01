package com.wbtcb.core.dto

import java.math.BigDecimal

data class TransactionOutput(
    val address: String,
    val amount: BigDecimal,
    val vout: Long
)
