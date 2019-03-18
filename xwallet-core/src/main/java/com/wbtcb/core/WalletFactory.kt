package com.wbtcb.core

import java.lang.IllegalArgumentException

object WalletFactory {

    @Suppress("UNCHECKED_CAST")
    fun createWallet(walletClassName: String, walletSpecification: WalletSpecification? = null): Wallet {
        try {
            val walletProviderClass = Class.forName(walletClassName)
            if (Wallet::class.java.isAssignableFrom(walletProviderClass)) {
                return createWallet((walletProviderClass as Class<Wallet>), walletSpecification)
            }
            throw IllegalArgumentException("Class '$walletClassName' does not implement Wallet")
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("Problem creating Wallet (class not found)", e)
        }
    }

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
