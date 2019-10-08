package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@Deprecated("Do not use use MutableList<HashMap<String, BigDecimal>>")
@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinAddressOutput(

    val address: MutableList<HashMap<String, BigDecimal>> = mutableListOf()

    /*
        @get:JsonAnyGetter
        val address: HashMap<String, BigDecimal> = HashMap()
    */
)
