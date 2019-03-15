package cz.smdev.core

data class WalletSpecification(
    var user: String = "",
    var password: String = "",
    var host: String = "",
    var port: String = "",
    var https: Boolean = false
)
