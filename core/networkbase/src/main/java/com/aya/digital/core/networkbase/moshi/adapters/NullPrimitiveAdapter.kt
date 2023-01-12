package com.aya.digital.core.networkbase.moshi.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import java.io.IOException

class NullPrimitiveAdapter {
    @FromJson
    @Throws(IOException::class)
    fun intFromJson(reader: JsonReader): Int {
        if (reader.peek() == JsonReader.Token.NUMBER || reader.peek() == JsonReader.Token.STRING) return reader.nextInt()
        reader.skipValue()
        return 0
    }

    @FromJson
    @Throws(IOException::class)
    fun booleanFromJson(reader: JsonReader): Boolean {
        if (reader.peek() == JsonReader.Token.BOOLEAN || reader.peek() == JsonReader.Token.STRING) return reader.nextBoolean()
        reader.skipValue()
        return false

    }

    @FromJson
    @Throws(IOException::class)
    fun doubleFromJson(reader: JsonReader): Double {
        if (reader.peek() == JsonReader.Token.NUMBER || reader.peek() == JsonReader.Token.STRING) return reader.nextDouble()
        reader.skipValue()
        return 0.0
    }

    @FromJson
    @Throws(IOException::class)
    fun floatFromJson(reader: JsonReader): Float {
        if (reader.peek() == JsonReader.Token.NUMBER || reader.peek() == JsonReader.Token.STRING) return reader.nextString()
            .toFloat()
        reader.skipValue()
        return 0.0F
    }
}