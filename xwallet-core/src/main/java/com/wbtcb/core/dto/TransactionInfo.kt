package com.wbtcb.core.dto

import java.math.BigDecimal
import java.util.Date

data class TransactionInfo(
    val txId: String,
    val amount: BigDecimal,
    val fee: BigDecimal?,
    val confirmations: Long,
    val blockHash: String?,
    val blockIndex: Long?,
    val comment: String?,
    val commentTo: String?,
    val time: Date,
    val blockTime: Date,
    val timeReceived: Date,
    val walletConflicts: List<String>,
    val inputs: List<TransactionInput> = emptyList(),
    val outputs: List<TransactionOutput> = emptyList()
)
