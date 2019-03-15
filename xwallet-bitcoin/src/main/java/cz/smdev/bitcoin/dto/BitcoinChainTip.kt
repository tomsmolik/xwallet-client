package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinChainTip(
    val height: Int? = null,
    val hash: String? = null,
    val branchlen: Int? = null,
    val status: String? = null
)
