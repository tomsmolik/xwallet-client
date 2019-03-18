package com.wbtcb.core

import com.wbtcb.core.enum.WalletCurrency
import com.wbtcb.core.service.wallet.WalletCoreService

interface WalletCore {

    var walletCurrency: WalletCurrency

    var walletCoreService: WalletCoreService

    fun walletSpecification(): WalletCoreSpecification

    fun defaultWalletSpecification(): WalletCoreSpecification

    fun applySpecification(walletSpecification: WalletCoreSpecification?)
}
