package com.wbtcb.bitcoin.service

import com.wbtcb.bitcoin.exception.BitcoinWalletException
import com.wbtcb.bitcoin.dto.BitcoinAddressValidation
import com.wbtcb.bitcoin.dto.BitcoinSmartFee
import com.wbtcb.bitcoin.dto.BitcoinTransaction
import com.wbtcb.bitcoin.dto.BitcoinTransactionInfo
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.bitcoin.dto.BitcoinWalletInfo
import com.wbtcb.core.WalletCore
import com.googlecode.jsonrpc4j.JsonRpcClientException
import java.math.BigDecimal

open class BitcoinWalletServiceRaw(wallet: WalletCore) : BitcoinBaseService(wallet) {

    @Throws(BitcoinWalletException::class)
    fun unlockBitcoinWallet(passphrase: String, timeoutSec: Long) {
        try {
            bitcoinRpcClient.walletPassphrase(passphrase, timeoutSec)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun lockBitcoinWallet() {
        try {
            bitcoinRpcClient.walletLock()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinWalletInfo(): BitcoinWalletInfo {
        try {
            return bitcoinRpcClient.getWalletInfo()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun setBitcoinWalletTransactionFee(fee: BigDecimal): Boolean {
        try {
            return bitcoinRpcClient.setTransactionFee(fee)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getNewBitcoinAddress(): String {
        try {
            return bitcoinRpcClient.getNewAddress()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun isBitcoinAddressValid(address: String): BitcoinAddressValidation {
        try {
            return bitcoinRpcClient.validateAddress(address)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinWalletBalance(minConfirmations: Long): BigDecimal {
        try {
            return bitcoinRpcClient.getBalance(minConf = minConfirmations)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinWalletUnconfirmedBalance(): BigDecimal {
        try {
            return bitcoinRpcClient.getUnconfirmedBalance()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinUnspentTransactions(address: List<String>, minConfirmations: Long): List<BitcoinUnspentTransaction> {
        try {
            return bitcoinRpcClient.listUnspent(addresses = address, minConfirmations = minConfirmations)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun sendBitcoinToAddress(address: String, amount: BigDecimal, comment: String?, commentTo: String?): String {
        try {
            return bitcoinRpcClient.sendToAddress(address = address, amount = amount, comment = comment, commentTo = commentTo)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinTransactions(limit: Int, offset: Int): List<BitcoinTransaction> {
        try {
            return bitcoinRpcClient.listTransactions(count = limit, from = offset)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinTransactionInfo(txId: String): BitcoinTransactionInfo {
        try {
            return bitcoinRpcClient.getWalletTransaction(txId)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun verifyBitcoinChain(checkLevel: Int, numBlocks: Int): Boolean {
        try {
            return bitcoinRpcClient.verifyChain(checkLevel, numBlocks)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun bitcoinKeyPoolRefill() {
        try {
            bitcoinRpcClient.keyPoolRefill()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun estimateBitcoinSmartFee(minConfirmations: Long): BitcoinSmartFee {
        try {
            return bitcoinRpcClient.estimateSmartFee(minConfirmations)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }
}
