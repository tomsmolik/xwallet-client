package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransactionInput(
    val txid: String? = null,
    val vout: Long? = null,
    val scriptSig: BitcoinScriptSignature? = null,
    val txinwitness: List<String>? = null,
    val coinbase: String? = null,
    val sequence: Long? = null
)
