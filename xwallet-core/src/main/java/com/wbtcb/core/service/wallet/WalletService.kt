package com.wbtcb.core.service.wallet

import com.wbtcb.core.dto.WalletInfo
import com.wbtcb.core.service.BaseService

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
