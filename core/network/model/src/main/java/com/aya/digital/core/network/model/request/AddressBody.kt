package com.aya.digital.core.network.model.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressBody(
    @Json(name = "address_line")
    val addressLine: String?=null,
    val lat:Double?=null,
    val long:Double?=null
)