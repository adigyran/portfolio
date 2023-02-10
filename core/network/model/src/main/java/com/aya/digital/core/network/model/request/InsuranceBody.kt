package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InsuranceBody(
    var id: Int,
    var name: String?
)