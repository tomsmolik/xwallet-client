package com.wbtcb.core.dto

import java.math.BigDecimal

data class WalletInfo(
    val walletName: String,
    val walletVersion: Long,
    val balance: BigDecimal,
    val unconfirmedBalance: BigDecimal,
    val immatureBalance: BigDecimal,
    val txCount: Long,
    val keyPoolOldest: Long,
    val keyPoolSize: Long,
    val keyPoolSizeHdInternal: Long,
    val unlockedUntil: Long,
    val payTxFee: BigDecimal?
)
