package com.wbtcb.bitcoin

import com.wbtcb.core.Wallet
import com.wbtcb.core.BaseWallet
import com.wbtcb.core.WalletSpecification
import com.wbtcb.core.service.wallet.WalletService
import com.wbtcb.bitcoin.service.BitcoinWalletService

class BitcoinWallet : BaseWallet(), Wallet {

    override var walletService: WalletService = BitcoinWalletService(this)

    override fun defaultWalletSpecification(): WalletSpecification {
        return WalletSpecification(
            host = "localhost",
            port = "18332"
        )
    }
}
