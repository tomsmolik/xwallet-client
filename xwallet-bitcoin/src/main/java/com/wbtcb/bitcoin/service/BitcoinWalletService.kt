package com.wbtcb.bitcoin.service

import com.wbtcb.bitcoin.dto.BitcoinBumpFeeOptions
import com.wbtcb.bitcoin.dto.BitcoinCategoryType
import com.wbtcb.bitcoin.dto.BitcoinCategoryType.Companion.toTransactionType
import com.wbtcb.bitcoin.dto.BitcoinTransactionInfoDetail
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.bitcoin.exception.BitcoinWalletException
import com.wbtcb.core.WalletCore
import com.wbtcb.core.dto.Address
import com.wbtcb.core.dto.NetworkInfo
import com.wbtcb.core.dto.Transaction
import com.wbtcb.core.dto.TransactionInfo
import com.wbtcb.core.dto.TransactionInput
import com.wbtcb.core.dto.TransactionOutput
import com.wbtcb.core.dto.WalletInfo
import com.wbtcb.core.service.wallet.WalletCoreService
import mu.KLogging
import java.math.BigDecimal
import java.math.RoundingMode

class BitcoinWalletService(wallet: WalletCore) : BitcoinClientService(wallet), WalletCoreService {

    override fun unlockWallet(passphrase: String, timeoutSec: Long) {
        unlockBitcoinWallet(passphrase, timeoutSec)
    }

    override fun lockWallet() {
        lockBitcoinWallet()
    }

    override fun getWalletInfo(): WalletInfo {
        getBitcoinWalletInfo().let { info ->
            return WalletInfo(
                walletName = info.walletName,
                walletVersion = info.walletVersion,
                balance = info.balance,
                unconfirmedBalance = info.unconfirmedBalance,
                immatureBalance = info.immatureBalance,
                txCount = info.txCount,
                keyPoolOldest = info.keyPoolOldest,
                keyPoolSize = info.keyPoolSize,
                keyPoolSizeHdInternal = info.keyPoolSizeHdInternal,
                unlockedUntil = info.unlockedUntil,
                payTxFee = info.payTxFee
            )
        }
    }

    override fun getNetworkInfo(): NetworkInfo {
        getBitcoinNetworkInfo().let { info ->
            return NetworkInfo(
                version = info.version,
                subversion = info.subversion,
                protocolVersion = info.protocolVersion,
                connections = info.connections
            )
        }
    }

    override fun setWalletTransactionFee(fee: BigDecimal): Boolean {
        return setBitcoinWalletTransactionFee(fee)
    }

    override fun estimateSmartFee(minConfirmations: Long): BigDecimal {
        return estimateBitcoinSmartFee(minConfirmations).feeRate
    }

    override fun getNewAddress(): String {
        return getNewBitcoinAddress()
    }

    override fun isAddressValid(address: String): Boolean {
        return isBitcoinAddressValid(address).isValid
    }

    override fun getWalletBalance(minConfirmations: Long): BigDecimal {
        return getBitcoinWalletBalance(minConfirmations)
    }

    override fun getWalletUnconfirmedBalance(): BigDecimal {
        return getBitcoinWalletUnconfirmedBalance()
    }

    override fun getAddressBalance(address: String, minConfirmations: Long): BigDecimal {
        return getBalanceFromUnspentTransactions(getBitcoinUnspentTransactions(listOf(address), minConfirmations))
    }

    override fun getAddressesBalance(addresses: List<String>, minConfirmations: Long): BigDecimal {
        return getBalanceFromUnspentTransactions(getBitcoinUnspentTransactions(addresses, minConfirmations))
    }

    override fun sendToAddress(address: String, amount: BigDecimal, comment: String?, commentTo: String?): String {
        return sendBitcoinToAddress(address, amount, comment, commentTo)
    }

    override fun getTransactions(limit: Int, offset: Int): List<Transaction> {
        return getBitcoinTransactions(limit, offset).map { transaction ->
            Transaction(
                txId = transaction.txId,
                address = transaction.address,
                type = transaction.category.toTransactionType(),
                amount = transaction.amount,
                fee = transaction.fee,
                vout = transaction.vout,
                confirmations = transaction.confirmations,
                blockHash = transaction.blockHash,
                blockIndex = transaction.blockIndex,
                comment = transaction.comment,
                commentTo = transaction.commentTo,
                otherAccount = transaction.otherAccount,
                time = transaction.time,
                blockTime = transaction.blockTime,
                timeReceived = transaction.timeReceived,
                walletConflicts = transaction.walletConflicts
            )
        }
    }

    override fun getAddressGroupings(): List<List<Address>> {
        return getBitcoinAddresses().map { groups ->
            groups.map { group ->
                Address(
                    group.address,
                    group.amount,
                    group.label
                )
            }
        }
    }

    override fun getAddresses(): List<Address> {
        return mutableListOf<Address>().apply {
            getBitcoinAddresses().map { groups ->
                groups.map { group ->
                    this.add(
                        Address(
                            group.address,
                            group.amount,
                            group.label
                        )
                    )
                }
            }
        }
    }

    override fun getTransactionInfo(txId: String): TransactionInfo {
        return getBitcoinTransactionInfo(txId).let { transaction ->
            TransactionInfo(
                txId = transaction.txId,
                amount = transaction.amount,
                fee = transaction.fee,
                confirmations = transaction.confirmations,
                blockHash = transaction.blockHash,
                blockIndex = transaction.blockIndex,
                comment = transaction.comment,
                commentTo = transaction.commentTo,
                time = transaction.time,
                blockTime = transaction.blockTime,
                timeReceived = transaction.timeReceived,
                walletConflicts = transaction.walletConflicts,
                inputs = getTransactionInputs(transaction.details),
                outputs = getTransactionOutputs(transaction.details)
            )
        }
    }

    override fun verifyChain(checkLevel: Int, numBlocks: Int): Boolean {
        return verifyBitcoinChain(checkLevel, numBlocks)
    }

    override fun keyPoolRefill() {
        bitcoinKeyPoolRefill()
    }

    override fun replaceByFees(txId: String, confTarget: BigDecimal?, totalFee: BigDecimal?): String {
        // init options params
        val options = BitcoinBumpFeeOptions()
        if (confTarget != null) {
            options.confTarget = confTarget
        }
        if (totalFee != null) {
            options.totalFee = totalFee
        }

        return bumpBitcoinFee(
            txId = txId,
            options = options
        ).txId!!
    }

    private fun prepareUnspentTransactions(amount: BigDecimal, unspentList: List<BitcoinUnspentTransaction>, feeRateAppender: BigDecimal): Pair<BigDecimal, List<com.wbtcb.core.dto.childPaysForParent.TransactionInput>> {
        // prepare TransactionInput
        val transactionInput = mutableListOf<com.wbtcb.core.dto.childPaysForParent.TransactionInput>()
        var amountTx = BigDecimal.ZERO
        unspentList.forEach {
            amountTx += it.amount
            transactionInput.add(
                com.wbtcb.core.dto.childPaysForParent.TransactionInput(
                    txid = it.txId,
                    vout = it.vout
                ))
            if (amountTx >= amount + approximateFee(transactionInput.size, 2, feeRateAppender))
                return Pair(
                    first = amountTx,
                    second = transactionInput
                )
        }
        throw BitcoinWalletException(-1, Throwable("Not found enough unspent transaction amountTx=$amountTx"))
    }

    private fun approximateFee(txInCount: Int, addOutCount: Int, feeRateAppender: BigDecimal): BigDecimal {
        // estimate fee rate in BTC/byte (switch KByte to Byte)
        val feeRate = estimateSmartFee(1).divide(1000.toBigDecimal())

        val fee = (feeRate + feeRateAppender) * ((txInCount * 150) + (addOutCount * 63) + 20).toBigDecimal().setScale(8, RoundingMode.FLOOR).stripTrailingZeros()

        logger.info { "Approximated fee: feeRate=$feeRate, txInCount= $txInCount, addOutCount=$addOutCount fee=$fee" }

        return fee
    }

    override fun sendToAddress(address: String, amount: BigDecimal, feeRateAppender: BigDecimal, comment: String?, commentTo: String?): String {
        logger.info { "Sent to address  address= $address, amount=$amount feeRateAppender=$feeRateAppender" }

        // get unspent Transaction
        val unspentList = getBitcoinUnspentTransactions(emptyList(), 1).filter { it.spendable }

        // prepare inputs
        val pair = prepareUnspentTransactions(amount, unspentList, feeRateAppender)
        val amountTx = pair.first
        val transactionInputs = pair.second

        // calculate fee
        val fee = approximateFee(transactionInputs.size, 2, feeRateAppender)

        // prepare output
        val addressOutput = mutableListOf<HashMap<String, BigDecimal>>()
        addressOutput.add(hashMapOf(address to amount))
        addressOutput.add(hashMapOf(getNewAddress() to (amountTx - amount - fee).stripTrailingZeros()))
        logger.info { "Prepared raw transaction transactionInputs= $transactionInputs, addressOutput=$addressOutput, amountTx=$amountTx, fee=$fee" }

        // createrawtransaction
        val txId = createBitcoinRawTransaction(
            inputs = transactionInputs,
            outputs = addressOutput
        )
        // sign transaction and send
        return sendBitcoinRawTransaction(
            txId = signBitcoinRawTransactionWithWallet(txId),
            allowHighFees = false
        )
    }

    override fun childPaysForParent(transactionInputs: List<com.wbtcb.core.dto.childPaysForParent.TransactionInput>, amount: BigDecimal, address: String): String {
        // prepare output
        val addressOutput = mutableListOf<HashMap<String, BigDecimal>>()
        addressOutput.add(hashMapOf(address to amount))

        // createrawtransaction
        val txId = createBitcoinRawTransaction(
            inputs = transactionInputs,
            outputs = addressOutput
        )
        // sign transaction and send
        return sendBitcoinRawTransaction(
            txId = signBitcoinRawTransactionWithWallet(txId),
            allowHighFees = false
        )
    }

    private fun getTransactionInputs(details: List<BitcoinTransactionInfoDetail>?): List<TransactionInput> {
        return arrayListOf<TransactionInput>().apply {
            details?.map { detail ->
                if (detail.category == BitcoinCategoryType.RECEIVE) {
                    add(TransactionInput(
                        detail.address,
                        detail.amount,
                        detail.vout
                    ))
                }
            }
        }
    }

    private fun getTransactionOutputs(details: List<BitcoinTransactionInfoDetail>?): List<TransactionOutput> {
        return arrayListOf<TransactionOutput>().apply {
            details?.map { detail ->
                if (detail.category == BitcoinCategoryType.SEND) {
                    add(TransactionOutput(
                        detail.address,
                        detail.amount,
                        detail.vout
                    ))
                }
            }
        }
    }

    private fun getBalanceFromUnspentTransactions(unspentTransactions: List<BitcoinUnspentTransaction>): BigDecimal {
        return unspentTransactions.map { it.amount }.fold(BigDecimal.ZERO, BigDecimal::add)
    }

    companion object : KLogging()
}
