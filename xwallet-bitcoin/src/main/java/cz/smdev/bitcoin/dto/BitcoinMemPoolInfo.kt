package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinMemPoolInfo(
    val size: Long? = null,
    val bytes: Long? = null,
    val usage: Long? = null,
    val maxmempool: Long? = null,
    val mempoolminfee: Long? = null
)
