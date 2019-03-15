package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinDecodedScript(
    val asm: String? = null,
    val hex: String? = null,
    val type: String? = null,
    val reqSigs: Int? = null,
    val addresses: List<String>? = null,
    val p2sh: String? = null
)
