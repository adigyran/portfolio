package com.aya.digital.core.network.model.response.doctors

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(
    val content: List<CityContent>,
) {
    @JsonClass(generateAdapter = true)
    data class CityContent(
        val id: Int,
        val name: String,
        val code: String
    )

    @JsonClass(generateAdapter = true)
    data class State(
        val id: Int,
        val name: String,
        val abbr: String
    )
}

