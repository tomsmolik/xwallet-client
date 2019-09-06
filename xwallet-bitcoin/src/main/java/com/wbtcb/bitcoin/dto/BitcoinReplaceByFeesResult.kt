package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinReplaceByFeesResult(

    @JsonProperty("txid")
    val txId: String?,

    @JsonProperty("origFee")
    val origFee: BigDecimal?,

    @JsonProperty("fee")
    val fee: BigDecimal?,

    @JsonProperty("errors")
    val errors: List<String>?
)
