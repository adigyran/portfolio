package com.aya.digital.core.networkbase

import com.aya.digital.core.networkbase.moshi.adapters.BigDecimalJsonAdapter
import com.aya.digital.core.networkbase.moshi.adapters.NullPrimitiveAdapter
import com.aya.digital.core.networkbase.server.IServerError
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.lang.NullPointerException
import java.lang.reflect.Type

object CommonUtils {

    private val moshi: Moshi =
        Moshi.Builder()
            .add(NullPrimitiveAdapter())
            .add(BigDecimalJsonAdapter())
            .add(KotlinJsonAdapterFactory()).build()

    @Throws(JsonDataException::class, IOException::class)
    fun <T> fromJson(json: String, typeOfT: Type): T? = moshi.adapter<T>(typeOfT).fromJson(json)

    fun toJson(obj: Any): String = moshi.adapter<Any>(Object::class.java).toJson(obj)
    private val typeClassList = Types.newParameterizedType(List::class.java, String::class.java)
    private val typeClassMap =
        Types.newParameterizedType(Map::class.java, String::class.java, typeClassList)

    fun mapServerErrors(errorString: String): List<IServerError> {
        val errorResp = try {
            fromJson<Map<String, List<String>>>(errorString, typeClassMap)
                ?: mapOf()
        } catch (ex: JsonDataException) {
            mapOf()
        }

        return try {
            mutableListOf<IServerError>().apply {
                errorResp.entries.map { entry ->
                    entry.value.forEach { add(ServerError(entry.key, it)) }
                }
            }
        } catch (ex:NullPointerException)
        {
            mutableListOf<IServerError>()
        }
    }
}