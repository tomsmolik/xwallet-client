package cz.smdev.bitcoin.service

import cz.smdev.bitcoin.dto.BitcoinAddressValidation
import cz.smdev.bitcoin.dto.BitcoinWalletInfo
import cz.smdev.core.Wallet

open class BitcoinWalletServiceRaw(wallet: Wallet) : BitcoinBaseService(wallet) {

    fun getBitcoinWalletInfo(): BitcoinWalletInfo {
        return bitcoinRpcClient.getWalletInfo()
    }

    fun isBitcoinAddressValid(address: String): BitcoinAddressValidation {
        return bitcoinRpcClient.validateAddress(address)
    }
}
