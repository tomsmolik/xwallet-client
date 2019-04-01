package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinWalletInfo(

    @JsonProperty("walletname")
    val walletName: String,

    @JsonProperty("walletversion")
    val walletVersion: Long,

    @JsonProperty("balance")
    val balance: BigDecimal,

    @JsonProperty("unconfirmed_balance")
    val unconfirmedBalance: BigDecimal,

    @JsonProperty("immature_balance")
    val immatureBalance: BigDecimal,

    @JsonProperty("txcount")
    val txCount: Long,

    @JsonProperty("keypoololdest")
    val keyPoolOldest: Long,

    @JsonProperty("keypoolsize")
    val keyPoolSize: Long,

    @JsonProperty("keypoolsize_hd_internal")
    val keyPoolSizeHdInternal: Long,

    @JsonProperty("unlocked_until")
    val unlockedUntil: Long,

    @JsonProperty("paytxfee")
    val payTxFee: BigDecimal?,

    @JsonProperty("hdmasterkeyid")
    val hdMasterKeyId: String
)
