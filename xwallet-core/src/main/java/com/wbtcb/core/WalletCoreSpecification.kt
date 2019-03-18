package com.wbtcb.core

data class WalletCoreSpecification(
    var user: String = "",
    var password: String = "",
    var host: String = "",
    var port: String = "",
    var https: Boolean = false
)
