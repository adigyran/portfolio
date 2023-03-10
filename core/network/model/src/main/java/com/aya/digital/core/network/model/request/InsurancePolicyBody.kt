package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InsurancePolicyBody(
    val number: String,
    val organizationId: Int,
    val attachmentId:Int?
)