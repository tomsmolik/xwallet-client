package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinBip9SoftForkStats(
    val period: Long? = null,
    val threshold: Long? = null,
    val elapsed: Long? = null,
    val count: Long? = null,
    val possible: Boolean? = null
)
