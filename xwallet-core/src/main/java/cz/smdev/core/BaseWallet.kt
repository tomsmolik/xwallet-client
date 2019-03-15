package cz.smdev.core

abstract class BaseWallet : Wallet {

    private lateinit var walletSpecification: WalletSpecification

    override fun walletSpecification(): WalletSpecification = walletSpecification

    override fun applySpecification(walletSpecification: WalletSpecification?) {
        walletSpecification?.let {
            this.walletSpecification = walletSpecification
        } ?: run {
            this.walletSpecification = defaultWalletSpecification()
        }
    }
}
