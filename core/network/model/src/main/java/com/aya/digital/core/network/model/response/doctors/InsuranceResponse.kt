package com.aya.digital.core.network.model.response.doctors

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InsuranceResponse(
    val id: Int,
    val name:String?,
    val organizationName:String?
)
