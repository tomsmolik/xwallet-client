package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinSoftForkStatus(
    val id: String? = null,
    val version: Long? = null,
    val reject: BitcoinSoftForkRejection? = null
)
