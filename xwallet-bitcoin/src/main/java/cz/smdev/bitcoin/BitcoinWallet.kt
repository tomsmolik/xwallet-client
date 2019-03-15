package cz.smdev.bitcoin

import cz.smdev.core.Wallet
import cz.smdev.core.BaseWallet
import cz.smdev.core.WalletSpecification
import cz.smdev.core.service.wallet.WalletService
import cz.smdev.bitcoin.service.BitcoinWalletService

class BitcoinWallet : BaseWallet(), Wallet {

    override var walletService: WalletService = BitcoinWalletService(this)

    override fun defaultWalletSpecification(): WalletSpecification {
        return WalletSpecification(
            host = "localhost",
            port = "18332"
        )
    }
}
