package com.aya.digital.core.network.model.response.doctors

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResponse(
    val lat: Double? = null,
    val long: Double? = null
)
