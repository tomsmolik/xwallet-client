package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinNetworkInfo(

    @JsonProperty("version")
    val version: Long,

    @JsonProperty("subversion")
    val subversion: String,

    @JsonProperty("protocolversion")
    val protocolVersion: Long,

    @JsonProperty("connections")
    val connections: Int
)
