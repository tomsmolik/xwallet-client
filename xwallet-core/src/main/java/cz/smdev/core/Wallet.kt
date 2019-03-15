package cz.smdev.core

import cz.smdev.core.service.wallet.WalletService

interface Wallet {

    var walletService: WalletService

    fun walletSpecification(): WalletSpecification

    fun defaultWalletSpecification(): WalletSpecification

    fun applySpecification(walletSpecification: WalletSpecification?)
}
