package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinTransaction(
    var txid: String = "",
    var address: String? = null,
    var category: BitcoinCategoryType? = null,
    var amount: BigDecimal? = null,
    var fee: BigDecimal? = null,
    var vout: Int = 0,
    var confirmations: Long? = null,
    var blockhash: String? = null,
    var blockindex: Long? = null,
    var blocktime: Date? = null,
    var time: Date? = null,
    var timereceived: Date? = null,
    var comment: String? = null,
    var otheraccount: String? = null,
    var to: String? = null,
    var walletconflicts: List<String>? = null
)
