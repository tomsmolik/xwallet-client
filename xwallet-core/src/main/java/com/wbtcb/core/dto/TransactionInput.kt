package com.wbtcb.core.dto

import java.math.BigDecimal

data class TransactionInput(
    var address: String?,
    var amount: BigDecimal = BigDecimal.ZERO,
    var vout: Long = 0
)
