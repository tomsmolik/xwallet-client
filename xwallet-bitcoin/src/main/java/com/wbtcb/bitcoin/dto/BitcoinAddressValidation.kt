package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinAddressValidation(

    @JsonProperty("isvalid")
    val isValid: Boolean,

    @JsonProperty("address")
    val address: String
)
