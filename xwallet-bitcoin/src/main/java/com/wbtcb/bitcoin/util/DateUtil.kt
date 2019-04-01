package com.wbtcb.bitcoin.util

import java.util.Date

object DateUtil {

    fun Long.epochToDate(): Date = Date(this * 1000)
}
