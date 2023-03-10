package com.aya.digital.core.network.model.response.profile

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InsuranceCompanyResponse(
    var id: Int,
    var nameOrg: String?
)