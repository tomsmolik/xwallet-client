package com.wbtcb.core.dto

import com.wbtcb.core.enum.TransactionType
import java.math.BigDecimal
import java.util.Date

data class Transaction(
    val txId: String,
    val address: String,
    val type: TransactionType,
    val amount: BigDecimal,
    val fee: BigDecimal?,
    val vout: Int = 0,
    val confirmations: Long,
    val blockHash: String,
    val blockIndex: Long,
    val comment: String?,
    val commentTo: String?,
    val otherAccount: String?,
    val time: Date,
    val blockTime: Date,
    val timeReceived: Date,
    val walletConflicts: List<String>
)
