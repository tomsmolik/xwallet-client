package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinNetwork(
    val name: String? = null,
    val limited: Boolean? = null,
    val reachable: Boolean? = null,
    val proxy: String? = null,
    val proxy_randomize_credentials: Boolean? = null
)
