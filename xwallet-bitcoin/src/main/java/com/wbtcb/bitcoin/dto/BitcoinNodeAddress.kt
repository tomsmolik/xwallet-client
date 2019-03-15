package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinNodeAddress(
    val address: String? = null,
    val connected: String? = null
)
