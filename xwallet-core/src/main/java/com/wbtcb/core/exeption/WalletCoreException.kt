package com.wbtcb.core.exeption

open class WalletCoreException(private val code: Int, cause: Throwable) : Exception(cause) {

    fun getType(): WalletCoreExceptionType = WalletCoreExceptionType.getByCode(code)
}
