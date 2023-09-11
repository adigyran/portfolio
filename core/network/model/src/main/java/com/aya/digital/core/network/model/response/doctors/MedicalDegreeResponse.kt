package com.aya.digital.core.network.model.response.doctors

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MedicalDegreeResponse(
    val id: Int,
    val code: String,
    val name: String,
)
