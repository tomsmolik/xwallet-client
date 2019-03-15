package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinBytesPerMessage(
    val alert: Long? = null,
    val filterload: Long? = null,
    val getheaders: Long? = null,
    val getblocks: Long? = null,
    val mempool: Long? = null,
    val ping: Long? = null,
    val pong: Long? = null,
    val verack: Long? = null,
    val inv: Long? = null,
    val version: Long? = null
)
