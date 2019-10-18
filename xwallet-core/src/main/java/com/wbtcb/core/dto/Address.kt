package com.wbtcb.core.dto

import java.math.BigDecimal

data class Address(
    val address: String,
    val amount: BigDecimal,
    val label: String?
)
