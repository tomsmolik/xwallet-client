package cz.smdev.core.dto

import java.math.BigDecimal

data class WalletInfo(
    var walletName: String? = null,
    var walletVersion: Long = 0,
    var balance: BigDecimal? = null,
    var unconfirmedBalance: BigDecimal? = null,
    var immatureBalance: BigDecimal? = null,
    var txCount: Long = 0,
    var keyPoolOldest: Long = 0,
    var keyPoolSize: Long = 0,
    var keyPoolSizeHdInternal: Long = 0,
    var unlockedUntil: Long = 0,
    var payTxFee: BigDecimal? = null,
    var hdMasterKeyId: String? = null
)
