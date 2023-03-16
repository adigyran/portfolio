package com.aya.digital.core.network.model.response.profile

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InsurancePolicyResponse(
    val id:Int,
    val number: String,
    val organizationId: Int,
    val organizationName:String,
    val fullUrl:String
)