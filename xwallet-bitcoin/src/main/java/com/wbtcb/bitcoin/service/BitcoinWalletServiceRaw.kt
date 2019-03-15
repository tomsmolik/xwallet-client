package com.wbtcb.bitcoin.service

import com.wbtcb.bitcoin.dto.BitcoinAddressValidation
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.bitcoin.dto.BitcoinWalletInfo
import com.wbtcb.core.Wallet

import java.math.BigDecimal

open class BitcoinWalletServiceRaw(wallet: Wallet) : BitcoinBaseService(wallet) {

    fun unlockBitcoinWallet(passphrase: String, timeoutSec: Long) {
        bitcoinRpcClient.walletPassphrase(passphrase, timeoutSec)
    }

    fun lockBitcoinWallet() {
        bitcoinRpcClient.walletLock()
    }

    fun getBitcoinWalletInfo(): BitcoinWalletInfo {
        return bitcoinRpcClient.getWalletInfo()
    }

    fun setBitcoinWalletTransactionFee(fee: BigDecimal): Boolean {
        return bitcoinRpcClient.setTransactionFee(fee)
    }

    fun getNewBitcoinAddress(): String {
        return bitcoinRpcClient.getNewAddress()
    }

    fun isBitcoinAddressValid(address: String): BitcoinAddressValidation {
        return bitcoinRpcClient.validateAddress(address)
    }

    fun getBitcoinWalletBalance(minConfirmations: Long): BigDecimal {
        return bitcoinRpcClient.getBalance(minConf = minConfirmations)
    }

    fun getBitcoinWalletUnconfirmedBalance(): BigDecimal {
        return bitcoinRpcClient.getUnconfirmedBalance()
    }

    fun getBitcoinUnspentTransactions(address: List<String>, minConfirmations: Long): List<BitcoinUnspentTransaction> {
        return bitcoinRpcClient.listUnspent(addresses = address, minConfirmations = minConfirmations)
    }

    fun sendBitcoinToAddress(address: String, amount: BigDecimal, comment: String?, commentTo: String?): String {
        return bitcoinRpcClient.sendToAddress(address = address, amount = amount, comment = comment, commentTo = commentTo)
    }
}
