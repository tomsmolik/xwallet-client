package com.wbtcb.bitcoin.service

import com.googlecode.jsonrpc4j.JsonRpcClientException
import com.wbtcb.core.WalletCore
import com.wbtcb.core.service.BaseWalletCoreService
import com.wbtcb.bitcoin.client.BitcoinRpcClientFactory
import com.wbtcb.bitcoin.dto.BitcoinAddressOutput
import com.wbtcb.bitcoin.dto.BitcoinAddressValidation
import com.wbtcb.bitcoin.dto.BitcoinBumpFeeOptions
import com.wbtcb.bitcoin.dto.BitcoinNetworkInfo
import com.wbtcb.bitcoin.dto.BitcoinReplaceByFeesResult
import com.wbtcb.bitcoin.dto.BitcoinSmartFee
import com.wbtcb.bitcoin.dto.BitcoinTransaction
import com.wbtcb.bitcoin.dto.BitcoinTransactionInfo
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.bitcoin.dto.BitcoinWalletInfo
import com.wbtcb.bitcoin.exception.BitcoinWalletException
import com.wbtcb.core.dto.childPaysForParent.TransactionInput
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
    fun getBitcoinNetworkInfo(): BitcoinNetworkInfo {
        try {
            return client.getNetworkInfo()
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
    fun bumpBitcoinFee(txId: String, options: BitcoinBumpFeeOptions?): BitcoinReplaceByFeesResult {
        try {
            val result = client.bumpFee(txId = txId, options = options)
            if (result.errors != null && result.errors.isNotEmpty()) {
                throw BitcoinWalletException(-1, Throwable(result.errors.toString()))
            }
            return result
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun createBitcoinRawTransaction(inputs: List<TransactionInput>, outputs: List<BitcoinAddressOutput>): String {
        try {
            return client.createRawTransaction(
                inputs = inputs,
                outputs = outputs)
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Deprecated(message = "Signrawtransaction is deprecated and will be fully removed in v0.18")
    @Throws(BitcoinWalletException::class)
    fun signBitcoinRawTransaction(txId: String): String {
        try {
            val signResult = client.signRawTransaction(
                txId = txId
            )
            if (!signResult.complete) {
                throw BitcoinWalletException(-1, Throwable("Signing process is incomplete, txId:" + signResult.txId))
            }
            return signResult.txId
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun signRawTransactionWithWallet(txId: String): String {
        try {
            val signResult = client.signRawTransactionWithWallet(
                txId = txId
            )
            if (!signResult.complete) {
                throw BitcoinWalletException(-1, Throwable("Signing process is incomplete, txId:" + signResult.txId))
            }
            return signResult.txId
        } catch (ex: JsonRpcClientException) {
            throw BitcoinWalletException(ex.code, ex)
        }
    }

    @Throws(BitcoinWalletException::class)
    fun sendBitcoinRawTransaction(txId: String, allowHighFees: Boolean): String {
        try {
            return client.sendRawTransaction(
                txId = txId,
                allowHighFees = allowHighFees
            )
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
