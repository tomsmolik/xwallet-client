package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinBlockInfoWithTransactions(
    val chain: String? = null,
    val hash: String? = null,
    val confirmations: Long? = null,
    val size: Long? = null,
    val strippedsize: Long? = null,
    val weight: Long? = null,
    val height: Long? = null,
    val version: Long? = null,
    val versionHex: String? = null,
    val merkleroot: String? = null,
    val tx: List<BitcoinTransaction>? = null,
    val rawtx: List<BitcoinTransaction>? = null,
    val time: Long? = null,
    val mediantime: Long? = null,
    val nonce: Long? = null,
    val bits: String? = null,
    val difficulty: BigDecimal? = null,
    val chainwork: String? = null,
    val previousblockhash: String? = null,
    val nextblockhash: String? = null
)
