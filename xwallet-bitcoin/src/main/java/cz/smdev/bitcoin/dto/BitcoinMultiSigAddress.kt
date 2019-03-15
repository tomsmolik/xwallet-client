package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinMultiSigAddress(
    val address: String? = null,
    val redeemScript: String? = null
)
