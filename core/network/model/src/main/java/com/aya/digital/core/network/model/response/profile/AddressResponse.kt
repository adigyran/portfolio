package com.aya.digital.core.network.model.response.profile


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressResponse(
    @Json(name = "address_line")
    val addressLine: String?,
    val lat:Double?,
    val long:Double?
)