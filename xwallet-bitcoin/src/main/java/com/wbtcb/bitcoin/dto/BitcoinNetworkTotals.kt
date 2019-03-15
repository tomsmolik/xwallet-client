package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinNetworkTotals(
    val totalbytesrecv: Long? = null,
    val totalbytessent: Long? = null,
    val timemillis: Long? = null,
    val uploadtarget: BitcoinNetworkUploadTarget? = null
)
