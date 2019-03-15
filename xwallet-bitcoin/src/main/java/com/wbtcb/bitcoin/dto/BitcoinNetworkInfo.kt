package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinNetworkInfo(
    val version: Long? = null,
    val subversion: String? = null,
    val protocolversion: Long? = null,
    val localservices: String? = null,
    val localrelay: Boolean? = null,
    val timeoffset: Int? = null,
    val connections: Int? = null,
    val networkactive: Boolean? = null,
    val networks: List<BitcoinNetwork>? = null,
    val relayfee: BigDecimal? = null,
    val incrementalfee: BigDecimal? = null,
    val localaddresses: List<BitcoinAddressInfo>? = null,
    val warnings: String? = null
)
