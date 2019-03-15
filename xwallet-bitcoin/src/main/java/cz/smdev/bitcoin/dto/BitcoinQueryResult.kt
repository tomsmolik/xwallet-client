package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinQueryResult(
    val txid: String? = null,
    val vout: Int? = null,
    val label: String? = null,
    val address: String? = null,
    val scriptPubKey: String? = null,
    val amount: BigDecimal? = null,
    val confirmations: Int? = null,
    val redeemScript: String? = null,
    val spendable: Boolean? = null,
    val solvable: Boolean? = null,
    val safe: Boolean? = null
)
