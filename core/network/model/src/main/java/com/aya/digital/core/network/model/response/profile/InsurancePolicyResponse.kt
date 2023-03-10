package com.aya.digital.core.network.model.response.profile

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InsurancePolicyResponse(
    val number: String,
    val organizationId: Int,
    val attachmentId:Int?
)