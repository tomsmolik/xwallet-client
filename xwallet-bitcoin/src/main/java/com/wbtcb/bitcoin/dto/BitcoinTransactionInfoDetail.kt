package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransactionInfoDetail(

    @JsonProperty("address")
    val address: String,

    @JsonProperty("amount")
    val amount: BigDecimal,

    @JsonProperty("category")
    val category: BitcoinCategoryType,

    @JsonProperty("fee")
    val fee: BigDecimal?,

    @JsonProperty("vout")
    val vout: Long
)
