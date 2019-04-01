package com.wbtcb.bitcoin.converter

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.wbtcb.bitcoin.util.DateUtil.epochToDate
import java.io.IOException
import java.util.Date

internal class UnixTimestampDeserializer : JsonDeserializer<Date>() {

    @Throws(IOException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Date = try {
        val timestamp = p.text.trim { it <= ' ' }
        java.lang.Long.valueOf(timestamp).epochToDate()
    } catch (e: NumberFormatException) {
        throw e
    }
}
