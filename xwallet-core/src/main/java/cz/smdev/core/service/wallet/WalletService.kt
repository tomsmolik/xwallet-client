package cz.smdev.core.service.wallet

import cz.smdev.core.dto.WalletInfo
import cz.smdev.core.service.BaseService

import java.io.IOException

interface WalletService : BaseService {

    @Throws(IOException::class)
    fun getWalletInfo(): WalletInfo {
        throw NotImplementedError()
    }

    @Throws(IOException::class)
    fun isAddressValid(address: String): Boolean {
        throw NotImplementedError()
    }
}
