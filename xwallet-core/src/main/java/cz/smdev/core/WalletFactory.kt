package cz.smdev.core

object WalletFactory {

    fun <W : Wallet> createWallet(walletClass: Class<W>, walletSpecification: WalletSpecification? = null): W {
        return createInstance(walletClass).apply {
            walletSpecification?.let {
                applySpecification(it)
            } ?: run {
                applySpecification(defaultWalletSpecification())
            }
        }
    }

    private fun <W : Wallet> createInstance(walletClass: Class<W>): W {
        return walletClass.newInstance()
    }
}
