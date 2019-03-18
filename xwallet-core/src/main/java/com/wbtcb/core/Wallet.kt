package com.wbtcb.core

import com.wbtcb.core.enum.Currency
import com.wbtcb.core.service.wallet.WalletService

interface Wallet {

    var currency: Currency

    var walletService: WalletService

    fun walletSpecification(): WalletSpecification

    fun defaultWalletSpecification(): WalletSpecification

    fun applySpecification(walletSpecification: WalletSpecification?)
}
