package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinQueryOptions(
    val minimumAmount: String? = null,
    val maximumAmount: String? = null,
    val maximumCount: String? = null,
    val minimumSumAmount: String? = null
)
