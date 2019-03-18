package com.wbtcb.bitcoin.client

import com.googlecode.jsonrpc4j.JsonRpcClientException
import com.googlecode.jsonrpc4j.JsonRpcMethod

import com.wbtcb.bitcoin.dto.BitcoinAddressValidation
import com.wbtcb.bitcoin.dto.BitcoinFeeEstimateMode
import com.wbtcb.bitcoin.dto.BitcoinQueryOptions
import com.wbtcb.bitcoin.dto.BitcoinSmartFee
import com.wbtcb.bitcoin.dto.BitcoinTransaction
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.bitcoin.dto.BitcoinWalletInfo
import com.wbtcb.bitcoin.dto.BitcoinTransactionInfo

import java.math.BigDecimal

interface BitcoinRpcClient {

    @JsonRpcMethod("getwalletinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getWalletInfo(): BitcoinWalletInfo

    @JsonRpcMethod("walletpassphrase")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun walletPassphrase(passphrase: String, timeoutSec: Long)

    @JsonRpcMethod("walletlock")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun walletLock()

    @JsonRpcMethod("settxfee")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun setTransactionFee(fee: BigDecimal): Boolean

    @JsonRpcMethod("getbalance")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBalance(
        account: String = "*",
        minConf: Long = 1,
        includeWatchOnly: Boolean = false
    ): BigDecimal

    @JsonRpcMethod("getunconfirmedbalance")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getUnconfirmedBalance(): BigDecimal

    @JsonRpcMethod("validateaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun validateAddress(address: String): BitcoinAddressValidation

    @JsonRpcMethod("getnewaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getNewAddress(): String

    @JsonRpcMethod("listunspent")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listUnspent(
        minConfirmations: Long? = null,
        maxConfirmations: Long? = null,
        addresses: List<String>? = null,
        includeUnsafe: Boolean? = null,
        queryOptions: BitcoinQueryOptions? = null
    ): List<BitcoinUnspentTransaction>

    @JsonRpcMethod("sendtoaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun sendToAddress(
        address: String,
        amount: BigDecimal,
        comment: String? = null,
        commentTo: String? = null,
        subtractFee: Boolean? = null,
        replaceable: Boolean? = null,
        minConfirmations: Int? = null,
        feeEstimateMode: BitcoinFeeEstimateMode? = null
    ): String

    @JsonRpcMethod("listtransactions")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listTransactions(
        account: String = "*",
        count: Int? = null,
        from: Int? = null
    ): List<BitcoinTransaction>

    @JsonRpcMethod("gettransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getWalletTransaction(txId: String): BitcoinTransactionInfo

    @JsonRpcMethod("verifychain")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun verifyChain(checkLevel: Int, numBlocks: Int): Boolean

    @JsonRpcMethod("keypoolrefill")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun keyPoolRefill()

    @JsonRpcMethod("estimatesmartfee")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun estimateSmartFee(minConfirmations: Long = 1): BitcoinSmartFee
}
