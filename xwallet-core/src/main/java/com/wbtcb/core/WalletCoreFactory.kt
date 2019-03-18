package com.wbtcb.core

import java.lang.IllegalArgumentException

object WalletCoreFactory {

    @Suppress("UNCHECKED_CAST")
    fun createWallet(walletClassName: String, walletSpecification: WalletCoreSpecification? = null): WalletCore {
        try {
            val walletProviderClass = Class.forName(walletClassName)
            if (WalletCore::class.java.isAssignableFrom(walletProviderClass)) {
                return createWallet((walletProviderClass as Class<WalletCore>), walletSpecification)
            }
            throw IllegalArgumentException("Class '$walletClassName' does not implement WalletCore")
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("Problem creating WalletCore (class not found)", e)
        }
    }

    fun <W : WalletCore> createWallet(walletClass: Class<W>, walletSpecification: WalletCoreSpecification? = null): W {
        return createInstance(walletClass).apply {
            walletSpecification?.let {
                applySpecification(it)
            } ?: run {
                applySpecification(defaultWalletSpecification())
            }
        }
    }

    private fun <W : WalletCore> createInstance(walletClass: Class<W>): W {
        return walletClass.newInstance()
    }
}
