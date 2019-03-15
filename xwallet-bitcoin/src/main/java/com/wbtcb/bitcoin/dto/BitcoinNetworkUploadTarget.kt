package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinNetworkUploadTarget(
    val timeframe: Long? = null,
    val target: Long? = null,
    val target_reached: Boolean? = null,
    val serve_historical_blocks: Boolean? = null,
    val bytes_left_in_cycle: Long? = null,
    val time_left_in_cycle: Long? = null
)
