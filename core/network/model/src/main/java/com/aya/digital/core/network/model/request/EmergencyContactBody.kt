package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmergencyContactBody(
    val fullName: String? = null,
    val phone: String? = null,
    val summary:String? = null,
    val typeId:Int?=null
)