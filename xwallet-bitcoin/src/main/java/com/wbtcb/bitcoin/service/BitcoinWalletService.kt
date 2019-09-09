package com.wbtcb.bitcoin.service

import com.wbtcb.bitcoin.dto.BitcoinAddressOutput
import com.wbtcb.bitcoin.dto.BitcoinBumpFeeOptions
import com.wbtcb.bitcoin.dto.BitcoinCategoryType
import com.wbtcb.bitcoin.dto.BitcoinCategoryType.Companion.toTransactionType
import com.wbtcb.bitcoin.dto.BitcoinTransactionInfoDetail
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.core.WalletCore
import com.wbtcb.core.dto.NetworkInfo
import com.wbtcb.core.dto.Transaction
import com.wbtcb.core.dto.TransactionInfo
import com.wbtcb.core.dto.TransactionInput
import com.wbtcb.core.dto.TransactionOutput
import com.wbtcb.core.dto.WalletInfo
import com.wbtcb.core.service.wallet.WalletCoreService
import java.math.BigDecimal

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

    override fun childPaysForParent(transactionInputs: List<com.wbtcb.core.dto.childPaysForParent.TransactionInput>, amount: BigDecimal, address: String): String {
        // prepare output
        val addressOutput = BitcoinAddressOutput()
        addressOutput.address[address] = amount

        // createrawtransaction
        val txId = createBitcoinRawTransaction(
            inputs = transactionInputs,
            outputs = listOf(addressOutput)
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
}
