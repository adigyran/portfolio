package com.aya.digital.core.network.model.response.emergencycontact

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmergencyContactResponse(
    val id: Int,
    val fullName: String? = null,
    val phone: String? = null,
    val summary: String? = null,
    val type:EmergencyContactTypeResponse?=null
)