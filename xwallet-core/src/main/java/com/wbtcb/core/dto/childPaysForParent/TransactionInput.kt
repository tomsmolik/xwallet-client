package com.wbtcb.core.dto.childPaysForParent

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class TransactionInput(

    @JsonProperty("txid")
    var txid: String,

    @JsonProperty("vout")
    var vout: Long
)
