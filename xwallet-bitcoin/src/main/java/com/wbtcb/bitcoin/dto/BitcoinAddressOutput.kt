package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinAddressOutput(

    @get:JsonAnyGetter
    val address: MutableList<HashMap<String, BigDecimal>> = mutableListOf()

    /*
        @get:JsonAnyGetter
        val address: HashMap<String, BigDecimal> = HashMap()
    */
)
