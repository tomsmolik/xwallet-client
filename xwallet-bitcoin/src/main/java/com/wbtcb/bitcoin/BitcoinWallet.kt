package com.wbtcb.bitcoin

import com.wbtcb.core.WalletCore
import com.wbtcb.core.BaseWalletCore
import com.wbtcb.core.WalletCoreSpecification
import com.wbtcb.core.service.wallet.WalletCoreService
import com.wbtcb.bitcoin.service.BitcoinWalletService
import com.wbtcb.core.enum.WalletCurrency

class BitcoinWallet : BaseWalletCore(), WalletCore {

    override var walletCurrency: WalletCurrency = WalletCurrency.BTC

    override var walletCoreService: WalletCoreService = BitcoinWalletService(this)

    override fun defaultWalletSpecification(): WalletCoreSpecification {
        return WalletCoreSpecification(
            host = "localhost",
            port = "18332"
        )
    }
}
