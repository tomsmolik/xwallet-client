package com.wbtcb.core.dto

import java.math.BigDecimal

data class TransactionInput(
    val address: String,
    val amount: BigDecimal,
    val vout: Long
)
