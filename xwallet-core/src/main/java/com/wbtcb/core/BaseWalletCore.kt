package com.wbtcb.core

abstract class BaseWalletCore : WalletCore {

    private lateinit var walletSpecification: WalletCoreSpecification

    override fun walletSpecification(): WalletCoreSpecification = walletSpecification

    override fun applySpecification(walletSpecification: WalletCoreSpecification?) {
        walletSpecification?.let {
            this.walletSpecification = walletSpecification
        } ?: run {
            this.walletSpecification = defaultWalletSpecification()
        }
    }
}
