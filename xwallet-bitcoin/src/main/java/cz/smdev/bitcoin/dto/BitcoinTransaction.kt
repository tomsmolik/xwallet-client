package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransaction(
    val txid: String? = null,
    val hash: String? = null,
    val size: Long? = null,
    val weight: Long? = null,
    val vsize: Long? = null,
    val version: Long? = null,
    val locktime: Long? = null,
    val vin: List<BitcoinTransactionInput>? = null,
    val vout: List<BitcoinTransactionOutput>? = null,
    val hex: String? = null,
    val blockhash: String? = null,
    val confirmations: Int? = null,
    val time: Long? = null,
    val blocktime: Long? = null
)
