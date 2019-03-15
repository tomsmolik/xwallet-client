package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinBlockChainInfo(
    val chain: String? = null,
    val blocks: Long? = null,
    val headers: Long? = null,
    val bestblockhash: String? = null,
    val difficulty: BigDecimal? = null,
    val mediantime: Long? = null,
    val verificationprogress: Long? = null,
    val chainwork: String? = null,
    val pruned: Boolean? = null,
    val pruneheight: Long? = null,
    val softforks: List<BitcoinSoftForkStatus>? = null,
    val bip9_softforks: Map<String, BitcoinBip9SoftForkStatus>? = null
)
