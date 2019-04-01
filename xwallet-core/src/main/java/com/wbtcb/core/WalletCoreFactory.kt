package com.wbtcb.core

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

object WalletCoreFactory {

    @Suppress("UNCHECKED_CAST")
    fun createWallet(walletClassName: String, walletSpecification: WalletCoreSpecification? = null): WalletCore {
        try {
            val walletProviderClass = Class.forName(walletClassName).kotlin
            if (walletProviderClass.isSubclassOf(WalletCore::class)) {
                return createWallet((walletProviderClass as KClass<WalletCore>), walletSpecification)
            }
            throw IllegalArgumentException("Class '$walletClassName' does not implement WalletCore")
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("Problem creating WalletCore (class not found)", e)
        }
    }

    fun <W : WalletCore> createWallet(walletClass: KClass<W>, walletSpecification: WalletCoreSpecification? = null): W {
        return createInstance(walletClass).apply {
            applySpecification(walletSpecification ?: defaultWalletSpecification())
        }
    }

    private fun <W : WalletCore> createInstance(walletClass: KClass<W>): W {
        return walletClass.createInstance()
    }
}
