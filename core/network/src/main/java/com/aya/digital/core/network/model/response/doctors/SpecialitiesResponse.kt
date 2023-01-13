package com.aya.digital.core.network.model.response.doctors

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpecialityResponse(
    val id: Int,
    val specialtyCode: String,
    val specialtyName: String,
)
