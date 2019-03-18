package com.wbtcb.core.dto

import java.math.BigDecimal
import java.util.Date

data class TransactionInfo(
    var txId: String = "",
    var amount: BigDecimal = BigDecimal.ZERO,
    var fee: BigDecimal? = BigDecimal.ZERO,
    var confirmations: Long = 0,
    var blockHash: String? = null,
    var blockIndex: Long = 0,
    var blockTime: Date? = null,
    var time: Date? = null,
    var timeReceived: Date? = null,
    var comment: String? = null,
    var conflictTxIds: List<String>? = emptyList(),
    var inputs: List<TransactionInput> = emptyList(),
    var outputs: List<TransactionOutput> = emptyList()
)
