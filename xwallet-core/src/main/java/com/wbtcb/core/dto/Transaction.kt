package com.wbtcb.core.dto

import java.math.BigDecimal
import java.util.Date

data class Transaction(
    var txId: String = "",
    var address: String = "",
    var amount: BigDecimal = BigDecimal.ZERO,
    var fee: BigDecimal? = null,
    var vout: Int = 0,
    var confirmations: Long? = null,
    var blockHash: String? = null,
    var blockIndex: Long? = null,
    var blockTime: Date? = null,
    var time: Date? = null,
    var timeReceived: Date? = null
)
