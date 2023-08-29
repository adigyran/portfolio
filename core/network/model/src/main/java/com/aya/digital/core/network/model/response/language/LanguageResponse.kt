package com.aya.digital.core.network.model.response.language

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LanguageResponse(
    val id: Int,
    val code: String,
    val name: String,
)
