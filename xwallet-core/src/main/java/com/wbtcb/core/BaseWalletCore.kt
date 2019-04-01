package com.wbtcb.core

abstract class BaseWalletCore : WalletCore {

    private lateinit var walletSpecification: WalletCoreSpecification

    override fun walletSpecification(): WalletCoreSpecification = walletSpecification

    override fun applySpecification(walletSpecification: WalletCoreSpecification?) {
        this.walletSpecification = walletSpecification ?: defaultWalletSpecification()
    }
}
