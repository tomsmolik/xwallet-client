package com.wbtcb.bitcoin.service

import com.googlecode.jsonrpc4j.JsonRpcClientException
import com.wbtcb.core.WalletCore
import com.wbtcb.core.service.BaseWalletCoreService
import com.wbtcb.bitcoin.client.BitcoinRpcClientFactory
import com.wbtcb.bitcoin.dto.BitcoinAddressValidation
import com.wbtcb.bitcoin.dto.BitcoinSmartFee
import com.wbtcb.bitcoin.dto.BitcoinTransaction
import com.wbtcb.bitcoin.dto.BitcoinTransactionInfo
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.bitcoin.dto.BitcoinWalletInfo
import com.wbtcb.bitcoin.exception.BitcoinWalletException
import java.math.BigDecimal

open class BitcoinClientService(wallet: WalletCore) : BaseWalletCoreService<WalletCore>(wallet) {

    private val client by lazy {
        wallet.walletSpecification().let { spec ->
            BitcoinRpcClientFactory.createClient(
                spec.user,
                spec.password,
                spec.host,
                spec.port,
                spec.https,
                spec.connectionTimeoutMillis,
                spec.readTimeoutMillis
            )
        }
    }

    @Throws(BitcoinWalletException::class)
    fun unlockBitcoinWallet(passphrase: String, timeoutSec: Long) {
        try {
            client.walletPassphrase(passphrase, timeoutSec)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    open fun lockBitcoinWallet() {
        try {
            client.walletLock()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinWalletInfo(): BitcoinWalletInfo {
        try {
            return client.getWalletInfo()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun setBitcoinWalletTransactionFee(fee: BigDecimal): Boolean {
        try {
            return client.setTransactionFee(fee)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getNewBitcoinAddress(): String {
        try {
            return client.getNewAddress()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun isBitcoinAddressValid(address: String): BitcoinAddressValidation {
        try {
            return client.validateAddress(address)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinWalletBalance(minConfirmations: Long): BigDecimal {
        try {
            return client.getBalance(minConf = minConfirmations)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinWalletUnconfirmedBalance(): BigDecimal {
        try {
            return client.getUnconfirmedBalance()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinUnspentTransactions(address: List<String>, minConfirmations: Long): List<BitcoinUnspentTransaction> {
        try {
            return client.listUnspent(addresses = address, minConfirmations = minConfirmations)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun sendBitcoinToAddress(address: String, amount: BigDecimal, comment: String?, commentTo: String?): String {
        try {
            return client.sendToAddress(address = address, amount = amount, comment = comment, commentTo = commentTo)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinTransactions(limit: Int, offset: Int): List<BitcoinTransaction> {
        try {
            return client.listTransactions(count = limit, from = offset)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun getBitcoinTransactionInfo(txId: String): BitcoinTransactionInfo {
        try {
            return client.getWalletTransaction(txId)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun verifyBitcoinChain(checkLevel: Int, numBlocks: Int): Boolean {
        try {
            return client.verifyChain(checkLevel, numBlocks)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun bitcoinKeyPoolRefill() {
        try {
            client.keyPoolRefill()
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun estimateBitcoinSmartFee(minConfirmations: Long): BitcoinSmartFee {
        try {
            return client.estimateSmartFee(minConfirmations)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }
}
