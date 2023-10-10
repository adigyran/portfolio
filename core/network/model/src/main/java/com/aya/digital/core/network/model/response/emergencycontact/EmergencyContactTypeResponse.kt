package com.aya.digital.core.network.model.response.emergencycontact

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmergencyContactTypeResponse(
    val id: Int,
    val code: String?,
    val name: String,
)