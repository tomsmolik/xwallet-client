package com.wbtcb.bitcoin.service

import com.wbtcb.bitcoin.dto.BitcoinCategoryType
import com.wbtcb.bitcoin.dto.BitcoinTransactionInfoDetail
import com.wbtcb.bitcoin.dto.BitcoinUnspentTransaction
import com.wbtcb.core.Wallet
import com.wbtcb.core.dto.Transaction
import com.wbtcb.core.dto.TransactionInfo
import com.wbtcb.core.dto.TransactionInput
import com.wbtcb.core.dto.TransactionOutput
import com.wbtcb.core.dto.WalletInfo
import com.wbtcb.core.service.wallet.WalletService

import java.math.BigDecimal

class BitcoinWalletService(wallet: Wallet) : BitcoinWalletServiceRaw(wallet), WalletService {

    override fun unlockWallet(passphrase: String, timeoutSec: Long) {
        unlockBitcoinWallet(passphrase, timeoutSec)
    }

    override fun lockWallet() {
        lockBitcoinWallet()
    }

    override fun getWalletInfo(): WalletInfo {
        getBitcoinWalletInfo().let { info ->
            return WalletInfo().copy(
                walletName = info.walletname,
                walletVersion = info.walletversion,
                balance = info.balance,
                unconfirmedBalance = info.unconfirmed_balance,
                immatureBalance = info.immature_balance,
                txCount = info.txcount,
                keyPoolOldest = info.keypoololdest,
                keyPoolSize = info.keypoolsize,
                keyPoolSizeHdInternal = info.keypoolsize_hd_internal,
                unlockedUntil = info.unlocked_until,
                payTxFee = info.paytxfee,
                hdMasterKeyId = info.hdmasterkeyid
            )
        }
    }

    override fun setWalletTransactionFee(fee: BigDecimal): Boolean {
        return setBitcoinWalletTransactionFee(fee)
    }

    override fun getNewAddress(): String {
        return getNewBitcoinAddress()
    }

    override fun isAddressValid(address: String): Boolean {
        return isBitcoinAddressValid(address).isvalid
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
            Transaction().copy(
                txId = transaction.txid,
                address = transaction.address!!,
                amount = transaction.amount!!,
                fee = transaction.fee,
                vout = transaction.vout,
                confirmations = transaction.confirmations,
                blockHash = transaction.blockhash,
                blockIndex = transaction.blockindex,
                blockTime = transaction.blocktime,
                time = transaction.time,
                timeReceived = transaction.timereceived
            )
        }
    }

    override fun getTransactionInfo(txId: String): TransactionInfo {
        return getBitcoinTransactionInfo(txId).let { transaction ->
            TransactionInfo().copy(
                txId = transaction.txid,
                amount = transaction.amount,
                fee = transaction.fee,
                confirmations = transaction.confirmations,
                blockHash = transaction.blockhash,
                blockIndex = transaction.blockindex,
                blockTime = transaction.blocktime,
                time = transaction.time,
                timeReceived = transaction.timereceived,
                comment = transaction.comment,
                inputs = getTransactionInputs(transaction.details),
                outputs = getTransactionOutputs(transaction.details)
            )
        }
    }

    private fun getTransactionInputs(details: List<BitcoinTransactionInfoDetail>?): List<TransactionInput> {
        return arrayListOf<TransactionInput>().apply {
            details?.map { detail ->
                if (detail.category == BitcoinCategoryType.receive) {
                    add(TransactionInput(
                        detail.address,
                        detail.amount
                    ))
                }
            }
        }
    }

    private fun getTransactionOutputs(details: List<BitcoinTransactionInfoDetail>?): List<TransactionOutput> {
        return arrayListOf<TransactionOutput>().apply {
            details?.map { detail ->
                if (detail.category == BitcoinCategoryType.send) {
                    add(TransactionOutput(
                        detail.address,
                        detail.amount
                    ))
                }
            }
        }
    }

    private fun getBalanceFromUnspentTransactions(unspentTransactions: List<BitcoinUnspentTransaction>): BigDecimal {
        return unspentTransactions.map { it.amount }.fold(BigDecimal.ZERO, BigDecimal::add)
    }
}
