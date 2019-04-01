package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.wbtcb.core.enum.TransactionType

enum class BitcoinCategoryType {

    @JsonProperty("send")
    SEND,

    @JsonProperty("receive")
    RECEIVE,

    @JsonProperty("generate")
    GENERATE,

    @JsonProperty("immature")
    IMMATURE,

    @JsonProperty("orphan")
    ORPHAN,

    @JsonProperty("move")
    MOVE;

    companion object {

        fun BitcoinCategoryType.toTransactionType(): TransactionType {
            TransactionType.values().forEach {
                if (name.toLowerCase() == it.name.toLowerCase()) {
                    return it
                }
            }
            return TransactionType.UNKNOWN
        }
    }
}
