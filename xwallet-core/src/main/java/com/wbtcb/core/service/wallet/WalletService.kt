package com.wbtcb.core.service.wallet

import com.wbtcb.core.dto.WalletInfo
import com.wbtcb.core.service.BaseService

import java.io.IOException
import java.math.BigDecimal

interface WalletService : BaseService {

    /**
     * Provides information about the wallet.
     *
     * @return information about the wallet
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun getWalletInfo(): WalletInfo {
        throw NotImplementedError()
    }

    /**
     * Decrypt the wallet for specified time, allowing changes of the wallet. The wallet must be encrypted. It will be
     * locked again after the specified time or after calling lock wallet.
     *
     * @param passphrase the passphrase that unlocks the wallet
     * @param timeoutSec the number of seconds after which the decryption key will be automatically deleted from memory
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun unlockWallet(passphrase: String, timeoutSec: Long) {
        throw NotImplementedError()
    }

    /**
     * Lock the wallet and purge the key from memory. The wallet must be encrypted first. Functions changing the wallet
     * can't be used until walletPassphrase is called after calling lock.
     *
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun lockWallet() {
        throw NotImplementedError()
    }

    /**
     * Set the transaction fee per kB.
     *
     * @param fee the fee to set
     * @return true if the fee was successfully set
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun setWalletTransactionFee(fee: BigDecimal): Boolean {
        throw NotImplementedError()
    }

    /**
     * Get a new address that can be used to receive funds. The address will be associated with the default account.
     *
     * @return new address
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun getNewAddress(): String {
        throw NotImplementedError()
    }

    /**
     * Address validation.
     *
     * @param address address to validate
     * @return true if the address is a valid
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun isAddressValid(address: String): Boolean {
        throw NotImplementedError()
    }

    /**
     * Get balance in the wallet, counting only transactions with at least given number of confirmation.
     *
     * @param minConfirmations minimal confirmations
     * @return the wallet balance
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun getWalletBalance(minConfirmations: Long = 1): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Get unconfirmed balance in the wallet.
     *
     * @return the wallet unconfirmed balance
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun getWalletUnconfirmedBalance(): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Get balance for the given address in the wallet, counting only transactions with at least given number of confirmation.
     *
     * @param address the address
     * @param minConfirmations minimal confirmations
     * @return the address balance
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun getAddressBalance(address: String, minConfirmations: Long = 1): BigDecimal {
        throw NotImplementedError()
    }

    /**
     * Get balance for the given addresses in the wallet, counting only transactions with at least given number of confirmation.
     *
     * @param addresses the addresses
     * @param minConfirmations minimal confirmations
     * @return the balance of the addresses
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
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
     * @throws IOException indication that a networking error occurred while fetching JSON data
     */
    @Throws(IOException::class)
    fun sendToAddress(address: String, amount: BigDecimal, comment: String? = null, commentTo: String? = null): String {
        throw NotImplementedError()
    }
}
