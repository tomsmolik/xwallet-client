package com.wbtcb.bitcoin.client

import com.googlecode.jsonrpc4j.JsonRpcClientException
import com.googlecode.jsonrpc4j.JsonRpcMethod

import com.wbtcb.bitcoin.dto.BitcoinAddedNodeInfo
import com.wbtcb.bitcoin.dto.BitcoinAddressValidation
import com.wbtcb.bitcoin.dto.BitcoinBlockChainInfo
import com.wbtcb.bitcoin.dto.BitcoinBlockInfo
import com.wbtcb.bitcoin.dto.BitcoinBlockInfoWithTransactions
import com.wbtcb.bitcoin.dto.BitcoinBlockTemplateRequest
import com.wbtcb.bitcoin.dto.BitcoinChainTip
import com.wbtcb.bitcoin.dto.BitcoinChainTransactionStats
import com.wbtcb.bitcoin.dto.BitcoinDecodedScript
import com.wbtcb.bitcoin.dto.BitcoinEstimateSmartFee
import com.wbtcb.bitcoin.dto.BitcoinFeeEstimateMode
import com.wbtcb.bitcoin.dto.BitcoinMemPoolInfo
import com.wbtcb.bitcoin.dto.BitcoinMiningInfo
import com.wbtcb.bitcoin.dto.BitcoinMultiSigAddress
import com.wbtcb.bitcoin.dto.BitcoinNetworkInfo
import com.wbtcb.bitcoin.dto.BitcoinNetworkTotals
import com.wbtcb.bitcoin.dto.BitcoinNodeListOperation
import com.wbtcb.bitcoin.dto.BitcoinOutPoint
import com.wbtcb.bitcoin.dto.BitcoinPeerInfo
import com.wbtcb.bitcoin.dto.BitcoinQueryOptions
import com.wbtcb.bitcoin.dto.BitcoinQueryResult
import com.wbtcb.bitcoin.dto.BitcoinSearchedTransactionResult
import com.wbtcb.bitcoin.dto.BitcoinTransaction
import com.wbtcb.bitcoin.dto.BitcoinUtxoSet
import com.wbtcb.bitcoin.dto.BitcoinWalletInfo

import java.math.BigDecimal

interface BitcoinRpcClient {

    @JsonRpcMethod("abortrescan")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun abortRescan()

    @JsonRpcMethod("addmultisigaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun addMultiSigAddress(required: Int? = null, keys: List<String>): String

    @JsonRpcMethod("addnode")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun addNode(address: String, operation: BitcoinNodeListOperation)

    @JsonRpcMethod("backupwallet")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun backupWallet(destination: String)

    @JsonRpcMethod("clearbanned")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun clearBanned()

    @JsonRpcMethod("createmultisig")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun createMultiSig(required: Int, keys: List<String>): BitcoinMultiSigAddress

    @JsonRpcMethod("createrawtransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun createRawTransaction(
        inputs: List<BitcoinOutPoint>,
        outputs: Map<String, BigDecimal>,
        lockTime: Int? = null,
        replaceable: Boolean? = null
    ): String

    @JsonRpcMethod("decoderawtransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun decodeRawTransaction(transactionId: String): BitcoinTransaction

    @JsonRpcMethod("decodescript")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun decodeScript(scriptHex: String): BitcoinDecodedScript

    @JsonRpcMethod("disconnectnode")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun disconnectNode(nodeAddress: String? = null, nodeId: Int? = null)

    @JsonRpcMethod("dumpprivkey")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun dumpPrivateKey(address: String): String

    @JsonRpcMethod("dumpwallet")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun dumpWallet(filename: String): Map<*, *>

    @JsonRpcMethod("encryptwallet")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun encryptWallet(passphrase: String)

    @JsonRpcMethod("generate")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun generate(numberOfBlocks: Int, maxTries: Int? = null): List<String>

    @JsonRpcMethod("getaddednodeinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getAddedNodeInfo(): List<BitcoinAddedNodeInfo>

    @JsonRpcMethod("getbalance")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBalance(
        account: String = "*",
        minConf: Int = 1,
        includeWatchOnly: Boolean = false
    ): BigDecimal

    @JsonRpcMethod("getbestblockhash")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBestBlockhash(): String

    @JsonRpcMethod("getblock")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlockData(blockHash: String, verbosity: Int = 0): String

    @JsonRpcMethod("getblock")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlock(blockHash: String, verbosity: Int = 1): BitcoinBlockInfo

    @JsonRpcMethod("getblock")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlockWithTransactions(blockHash: String, verbosity: Int = 2): BitcoinBlockInfoWithTransactions

    @JsonRpcMethod("getblockchaininfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlockchainInfo(): BitcoinBlockChainInfo

    @JsonRpcMethod("getblockcount")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlockCount(): Int

    @JsonRpcMethod("getblockhash")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlockHash(height: Int): String

    @JsonRpcMethod("getblockheader")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlockHeader(blockHash: String, verbose: Boolean? = false): Any

    @JsonRpcMethod("getblocktemplate")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getBlockTemplate(blockTemplateRequest: BitcoinBlockTemplateRequest? = null)

    @JsonRpcMethod("getchaintips")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getChainTips(): List<BitcoinChainTip>

    @JsonRpcMethod("getchaintxstats")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getChainTransactionStats(
        blockWindowSize: Int? = null,
        blockHashEnd: String? = null
    ): BitcoinChainTransactionStats

    @JsonRpcMethod("getconnectioncount")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getConnectionCount(): Int

    @JsonRpcMethod("getdifficulty")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getDifficulty(): BigDecimal

    @JsonRpcMethod("getmemoryinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getMemoryInfo(): Any

    @JsonRpcMethod("getmempoolancestors")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getMempoolAncestors(transactionId: String): Any

    @JsonRpcMethod("getmempooldescendants")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getMempoolDescendants(): Any

    @JsonRpcMethod("getmempoolentry")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getMempoolEntry(transactionId: String): Map<*, *>

    @JsonRpcMethod("getmempoolinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getMempoolInfo(): BitcoinMemPoolInfo

    @JsonRpcMethod("getmininginfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getMiningInfo(): BitcoinMiningInfo

    @JsonRpcMethod("getnettotals")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getNetworkTotals(): BitcoinNetworkTotals

    @JsonRpcMethod("getnetworkhashps")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getNetworkHashesPerSeconds(lastBlocks: Int, height: Int): Long

    @JsonRpcMethod("getnetworkinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getNetworkInfo(): BitcoinNetworkInfo

    @JsonRpcMethod("getnewaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getNewAddress(): String

    @JsonRpcMethod("getpeerinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getPeerInfo(): List<BitcoinPeerInfo>

    @JsonRpcMethod("getrawchangeaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getRawChangeAddress(): String

    @JsonRpcMethod("getrawmempool")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getRawMemPool(verbose: Boolean = false): List<Map<*, *>>

    @JsonRpcMethod("getrawtransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getRawTransaction(transactionId: String, verbosity: Int = 1): BitcoinTransaction

    @JsonRpcMethod("getreceivedbyaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getReceivedByAddress(address: String, minConfirmations: Int = 1): BigDecimal

    @JsonRpcMethod("gettransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getWalletTransaction(transactionId: String): Map<*, *>

    @JsonRpcMethod("gettxout")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getUnspentTransactionOutputInfo(transactionId: String, index: Int): Map<*, *>

    @JsonRpcMethod("gettxoutsetinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getUnspentTransactionOutputSetInfo(): BitcoinUtxoSet

    @JsonRpcMethod("getwalletinfo")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun getWalletInfo(): BitcoinWalletInfo

    @JsonRpcMethod("importaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun importAddress(
        scriptOrAddress: String,
        label: String? = null,
        rescan: Boolean? = null,
        includePayToScriptHash: Boolean? = null
    )

    @JsonRpcMethod("importprivkey")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun importPrivateKey(
        privateKey: String,
        label: String? = null,
        rescan: Boolean? = null
    )

    @JsonRpcMethod("importpubkey")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun importPublicKey(
        publicKey: String,
        label: String? = null,
        rescan: Boolean? = null
    )

    @JsonRpcMethod("importwallet")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun importWallet(walletFile: String)

    @JsonRpcMethod("keypoolrefill")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun keypoolRefill(newSize: Int = 100)

    @JsonRpcMethod("listaddressgroupings")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listAddressGroupings(): List<*>

    @JsonRpcMethod("listbanned")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listBanned(): List<String>

    @JsonRpcMethod("listlockunspent")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listLockUnspent(): List<Map<*, *>>

    @JsonRpcMethod("listreceivedbyaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listReceivedByAddress(
        minConfirmations: Int? = null,
        includeEmpty: Boolean? = null,
        includeWatchOnly: Boolean? = null
    ): List<Map<*, *>>

    @JsonRpcMethod("listsinceblock")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listSinceBlock(
        blockHash: String? = null,
        targetConfirmations: Int? = null,
        includeWatchOnly: Boolean? = null,
        includeRemoved: Boolean? = null
    ): Map<*, *>

    @JsonRpcMethod("listtransactions")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listTransactions(
        account: String? = null,
        count: Int? = null,
        skip: Int? = null,
        includeWatchOnly: Boolean? = null
    ): List<Map<*, *>>

    @JsonRpcMethod("listunspent")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listUnspent(
        minConfirmations: Int? = null,
        maxConfirmations: Int? = null,
        addresses: List<String>? = null,
        includeUnsafe: Boolean? = null,
        queryOptions: BitcoinQueryOptions? = null
    ): List<BitcoinQueryResult>

    @JsonRpcMethod("listwallets")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun listWallets(): List<String>

    @JsonRpcMethod("lockunspent")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun lockUnspent(unlock: Boolean, unspentOutputs: List<BitcoinOutPoint>): Boolean

    @JsonRpcMethod("preciousblock")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun preciousBlock(block: String)

    @JsonRpcMethod("prioritisetransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun prioritiseTransaction(transactionId: String, dummy: Int, feeDeltaSatoshis: Int)

    @JsonRpcMethod("pruneblockchain")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun pruneBlockchain(blockHeightOrUnixTimestamp: Long)

    @JsonRpcMethod("removeprunedfunds")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun removePrunedFunds(transactionId: String)

    @JsonRpcMethod("sendmany")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun sendMany(
        account: String,
        addressAmounts: Map<String, BigDecimal>,
        comment: String? = null,
        subtractFee: Boolean = false,
        replaceable: Boolean = false,
        minConfirmations: Int? = null,
        feeEstimateMode: BitcoinFeeEstimateMode? = null
    )

    @JsonRpcMethod("sendrawtransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun sendRawTransaction(transaction: String): String

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

    @JsonRpcMethod("setban")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun setBan(
        address: String,
        operation: BitcoinNodeListOperation,
        seconds: Int
    )

    @JsonRpcMethod("settxfee")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun setTransactionFee(fee: Double)

    @JsonRpcMethod("estimatesmartfee")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun estimateSmartFee(
        confTarget: Int,
        feeEstimateMode: BitcoinFeeEstimateMode? = BitcoinFeeEstimateMode.CONSERVATIVE
    ): BitcoinEstimateSmartFee

    @JsonRpcMethod("signmessage")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun signMessage(
        address: String,
        message: String
    )

    @JsonRpcMethod("signmessagewithprivkey")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun signMessageWithPrivateKey(
        privateKey: String,
        message: String
    )

    @JsonRpcMethod("signrawtransaction")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun signRawTransaction(transactionId: String)

    @JsonRpcMethod("submitblock")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun submitBlock(blockData: String)

    @JsonRpcMethod("validateaddress")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun validateAddress(address: String): BitcoinAddressValidation

    @JsonRpcMethod("verifychain")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun verifyChain(): Boolean

    @JsonRpcMethod("verifymessage")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun verifyMessage(
        address: String,
        signature: String,
        message: String
    )

    @JsonRpcMethod("searchrawtransactions")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun searchRawSerialisedTransactions(
        address: String,
        verbose: Int? = 0,
        skip: Int? = null,
        count: Int? = null,
        vInExtra: Int? = null,
        reverse: Boolean? = null
    ): List<String>

    @JsonRpcMethod("searchrawtransactions")
    @Throws(exceptionClasses = [JsonRpcClientException::class])
    fun searchRawVerboseTransactions(
        address: String,
        verbose: Int? = 1,
        skip: Int? = null,
        count: Int? = null,
        vInExtra: Int? = null,
        reverse: Boolean? = null
    ): List<BitcoinSearchedTransactionResult>
}
