package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DoctorLanguagesBody(
    val languageIds: List<Int>?
)