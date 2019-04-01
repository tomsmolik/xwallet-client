package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.wbtcb.bitcoin.converter.UnixTimestampDeserializer
import java.math.BigDecimal

import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransactionInfo(

    @JsonProperty("txid")
    val txId: String,

    @JsonProperty("amount")
    val amount: BigDecimal,

    @JsonProperty("fee")
    val fee: BigDecimal?,

    @JsonProperty("confirmations")
    val confirmations: Long,

    @JsonProperty("blockhash")
    val blockHash: String?,

    @JsonProperty("blockindex")
    val blockIndex: Long?,

    @JsonProperty("comment")
    val comment: String?,

    @JsonProperty("to")
    val commentTo: String?,

    @JsonProperty("time")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val time: Date,

    @JsonProperty("blocktime")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val blockTime: Date,

    @JsonProperty("timereceived")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val timeReceived: Date,

    @JsonProperty("walletconflicts")
    val walletConflicts: List<String>,

    @JsonProperty("details")
    val details: List<BitcoinTransactionInfoDetail>
)
