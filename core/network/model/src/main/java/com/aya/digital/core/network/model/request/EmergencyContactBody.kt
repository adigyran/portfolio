package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmergencyContactBody(
    val name: String? = null,
    val phone: String? = null
)