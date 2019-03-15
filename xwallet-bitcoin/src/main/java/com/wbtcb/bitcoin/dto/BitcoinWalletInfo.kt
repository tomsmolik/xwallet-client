package com.wbtcb.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinWalletInfo(
    var walletname: String? = null,
    var walletversion: Long = 0,
    var balance: BigDecimal? = null,
    var unconfirmed_balance: BigDecimal? = null,
    var immature_balance: BigDecimal? = null,
    var txcount: Long = 0,
    var keypoololdest: Long = 0,
    var keypoolsize: Long = 0,
    var keypoolsize_hd_internal: Long = 0,
    var unlocked_until: Long = 0,
    var paytxfee: BigDecimal? = null,
    var hdmasterkeyid: String? = null
)
