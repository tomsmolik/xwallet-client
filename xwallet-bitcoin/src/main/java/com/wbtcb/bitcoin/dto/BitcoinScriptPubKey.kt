package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinScriptPubKey(
    val asm: String? = null,
    val hex: String? = null,
    val reqSigs: Long? = null,
    val type: String? = null,
    val addresses: List<String>? = null
)
