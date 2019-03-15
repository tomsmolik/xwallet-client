package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinMiningInfo(
    val blocks: Long? = null,
    val currentblockweight: Long? = null,
    val currentblocktx: Long? = null,
    val difficulty: Long? = null,
    val errors: String? = null,
    val networkhashps: Long? = null,
    val pooledtx: Long? = null,
    val chain: String? = null
)
