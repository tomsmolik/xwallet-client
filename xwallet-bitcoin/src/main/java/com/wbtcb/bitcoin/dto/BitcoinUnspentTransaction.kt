package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinUnspentTransaction(

    @JsonProperty("txid")
    val txId: String,

    @JsonProperty("address")
    val address: String,

    @JsonProperty("amount")
    val amount: BigDecimal,

    @JsonProperty("confirmations")
    val confirmations: Int,

    @JsonProperty("spendable")
    val spendable: Boolean,

    @JsonProperty("solvable")
    val solvable: Boolean,

    @JsonProperty("vout")
    val vout: Long
)
