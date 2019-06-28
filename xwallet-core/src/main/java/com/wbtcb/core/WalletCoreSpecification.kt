package com.wbtcb.core

data class WalletCoreSpecification(
    var user: String = "",
    var password: String = "",
    var host: String = "",
    var port: String = "",
    var https: Boolean = false,
    var connectionTimeoutMillis: Int = DEFAULT_CONNECTION_TIMEOUT_MILLIS,
    var readTimeoutMillis: Int = DEFAULT_READ_TIMEOUT_MILLIS
) {

    companion object {
        const val DEFAULT_CONNECTION_TIMEOUT_MILLIS = 60 * 1000
        const val DEFAULT_READ_TIMEOUT_MILLIS = 60 * 1000 * 2
    }
}
