package com.aya.digital.core.networkbase.moshi.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.io.IOException
import java.math.BigDecimal

class BigDecimalJsonAdapter {
    @FromJson
    @Throws(IOException::class)
    fun fromJson(reader: JsonReader): BigDecimal? {
        if (reader.peek() == JsonReader.Token.NUMBER || reader.peek() == JsonReader.Token.STRING) return BigDecimal(
            reader.nextString()
        )
        reader.skipValue()
        return null
    }

    @ToJson
    @Throws(IOException::class)
    fun toJson(writer: JsonWriter, value: BigDecimal?) {
        writer.value(value?.toPlainString())
    }
}