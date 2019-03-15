package com.wbtcb.bitcoin.service

import com.wbtcb.bitcoin.dto.BitcoinAddressValidation
import com.wbtcb.bitcoin.dto.BitcoinWalletInfo
import com.wbtcb.core.Wallet

open class BitcoinWalletServiceRaw(wallet: Wallet) : BitcoinBaseService(wallet) {

    fun getBitcoinWalletInfo(): BitcoinWalletInfo {
        return bitcoinRpcClient.getWalletInfo()
    }

    fun isBitcoinAddressValid(address: String): BitcoinAddressValidation {
        return bitcoinRpcClient.validateAddress(address)
    }
}
