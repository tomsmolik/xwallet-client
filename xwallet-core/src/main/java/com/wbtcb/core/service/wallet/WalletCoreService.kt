package com.wbtcb.core.service.wallet

import com.wbtcb.core.dto.NetworkInfo
import com.wbtcb.core.dto.Transaction
import com.wbtcb.core.dto.TransactionInfo
import com.wbtcb.core.dto.WalletInfo
import com.wbtcb.core.exeption.WalletCoreException
import java.math.BigDecimal

interface WalletCoreService {

    /**
     * Provides information about the wallet.
     *
     * @return information about the wallet
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getWalletInfo(): WalletInfo {
        throw NotImplementedError()
    }

    /**
     * Provides information about the network.
     *
     * @return information about the network
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getNetworkInfo(): NetworkInfo {
        throw NotImplementedError()
    }

    /**
     * Decrypt the wallet for specified time, allowing changes of the wallet. The wallet must be encrypted. It will be
     * locked again after the specified time or after calling lock wallet.
     *
     * @param passphrase the passphrase that unlocks the wallet
     * @param timeoutSec the number of seconds after which the decryption key will be automatically deleted from memory
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun unlockWallet(passphrase: String, timeoutSec: Long) {
        throw NotImplementedError()
    }

    /**
     * Lock the wallet and purge the key from memory. The wallet must be encrypted first. Functions changing the wallet
     * can't be used until walletPassphrase is called after calling lock.
     *
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun lockWallet() {
        throw NotImplementedError()
    }

    /**
     * Set the transaction fee per kB.
     *
     * @param fee the fee to set
     * @return true if the fee was successfully set
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun setWalletTransactionFee(fee: BigDecimal): Boolean {
        throw NotImplementedError()
    }

    /**
     * Estimates the approximate fee per byte
     * @return estimate fee rate per byte
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun estimateSmartFee(minConfirmations: Long = 1): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Get a new address that can be used to receive funds. The address will be associated with the default account.
     *
     * @return new address
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getNewAddress(): String {
        throw NotImplementedError()
    }

    /**
     * Address validation.
     *
     * @param address address to validate
     * @return true if the address is a valid
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun isAddressValid(address: String): Boolean {
        throw NotImplementedError()
    }

    /**
     * Get balance in the wallet, counting only transactions with at least given number of confirmation.
     *
     * @param minConfirmations minimal confirmations
     * @return the wallet balance
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getWalletBalance(minConfirmations: Long = 1): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Get unconfirmed balance in the wallet.
     *
     * @return the wallet unconfirmed balance
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getWalletUnconfirmedBalance(): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Get balance for the given address in the wallet, counting only transactions with at least given number of confirmation.
     *
     * @param address the address
     * @param minConfirmations minimal confirmations
     * @return the address balance
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getAddressBalance(address: String, minConfirmations: Long = 1): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Get balance for the given addresses in the wallet, counting only transactions with at least given number of confirmation.
     *
     * @param addresses the addresses
     * @param minConfirmations minimal confirmations
     * @return the balance of the addresses
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getAddressesBalance(addresses: List<String>, minConfirmations: Long = 1): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Spends an amount to a given address.
     *
     * @param address the address to which the coins should be sent
     * @param amount the amount to spent
     * @param comment a locally-stored (not broadcast) comment assigned to this transaction
     * @param commentTo a locally-stored (not broadcast) comment assigned to this transaction.
     * @return The transaction id of the sent transaction, encoded as hex
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun sendToAddress(address: String, amount: BigDecimal, comment: String? = null, commentTo: String? = null): String {
        throw NotImplementedError()
    }

    /**
     * Returns the most recent transactions that affect the wallet.
     *
     * @param limit The number of the most recent transactions to list.
     * @param offset The number of the most recent transactions which should not be returned.
     * @return Most recent transactions that affect the wallet
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getTransactions(limit: Int, offset: Int): List<Transaction> {
        throw NotImplementedError()
    }

    /**
     *  Gets detailed information about an in-wallet transaction.
     *
     * @param txId id of the transaction to get details about
     * @return Detailed information about an in-wallet transaction
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun getTransactionInfo(txId: String): TransactionInfo {
        throw NotImplementedError()
    }

    /**
     *  Verifies each entry in the local block chain database.
     *
     * Chain levels:
     * 0. Read from disk to ensure the files are accessible
     * 1. Ensure each block is valid
     * 2. Make sure undo files can be read from disk and are in a valid format
     * 3. Test each block undo to ensure it results in correct state
     * 4. After undoing blocks, reconnect them to ensure they reconnect correctly
     *
     * @param checkLevel 0-4, default 3
     * @param numBlocks 0 = all, default 288
     * @return True if verified. False if verification failed for any reason
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun verifyChain(checkLevel: Int = 3, numBlocks: Int = 288): Boolean {
        throw NotImplementedError()
    }

    /**
     *  Fills the cache of unused pre-generated keys (the keypool).
     *
     * @throws WalletCoreException indication that a networking error occurred while fetching JSON data
     */
    @Throws(WalletCoreException::class)
    fun keyPoolRefill() {
        throw NotImplementedError()
    }
}
