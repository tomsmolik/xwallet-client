package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinBip9SoftForkStatus(
    val status: String? = null,
    val bit: Long? = null,
    val startTime: Long? = null,
    val timeout: Long? = null,
    val since: Long? = null,
    val statistics: BitcoinBip9SoftForkStats? = null
)
