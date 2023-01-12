package com.aya.digital.core.networkbase.moshi.adapters

import com.squareup.moshi.*
import com.squareup.moshi.JsonAdapter.Factory
import java.io.IOException
import java.util.*

internal class IntegerEnumJsonAdapter<T : Enum<*>> private constructor(enumType: Class<T>) :
    JsonAdapter<T>() {
    companion object {
        val FACTORY: Factory = Factory { type, annotations, moshi ->
            val rawType = Types.getRawType(type)
            if (rawType.isEnum) {
                return@Factory IntegerEnumJsonAdapter(rawType as Class<Enum<*>>)
            }
            null
        }
    }

    private val nameStrings: Array<String?>
    private val nameConstantMap: MutableMap<String, T>

    init {
        try {
            val constants = enumType.enumConstants!!
            nameStrings = arrayOfNulls(constants.size)
            nameConstantMap = LinkedHashMap()
            for (i in constants.indices) {
                val constant = constants[i]
                val annotation = enumType.getField(constant.name).getAnnotation(Json::class.java)
                val name = annotation?.name ?: constant.name
                nameConstantMap[name] = constant
                nameStrings[i] = name
            }
        } catch (e: NoSuchFieldException) {
            throw AssertionError("Missing field in ${enumType.name}")
        }
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): T? {
        if (reader.peek() == JsonReader.Token.NULL) {
            reader.skipValue()
            return null
        }

        val name = reader.nextString()
        val constant = nameConstantMap[name]
        if (constant != null) return constant
        throw JsonDataException(
            "Expected one of ${
                listOf(
                    *nameStrings
                )
            } " + "but was $name at path ${reader.path}"
        )
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: T?) {
        val newValue = nameConstantMap.filter { value == it.value }.map { it.key }.firstOrNull()
        if (newValue != null) writer.value(newValue) else writer.nullValue()
    }
}