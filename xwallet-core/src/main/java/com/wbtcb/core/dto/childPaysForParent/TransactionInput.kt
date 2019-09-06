package com.wbtcb.core.dto.childPaysForParent

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class TransactionInput(
    @JsonProperty("txid")
    var txId: String,

    var vout: Long?
)
