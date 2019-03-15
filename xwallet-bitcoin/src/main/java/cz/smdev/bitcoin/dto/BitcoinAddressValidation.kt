package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinAddressValidation(
    val isvalid: Boolean = false,
    val address: String? = null,
    val scriptPubKey: String? = null,
    val isscript: String? = null,
    val iswitness: String? = null
)
