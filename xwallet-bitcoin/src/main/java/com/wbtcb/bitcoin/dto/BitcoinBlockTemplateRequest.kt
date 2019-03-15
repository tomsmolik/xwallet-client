package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinBlockTemplateRequest(
    val mode: BitcoinBlockTemplateRequestMode? = null,
    val capabilities: List<String>? = null,
    val rules: List<String>? = null
)
