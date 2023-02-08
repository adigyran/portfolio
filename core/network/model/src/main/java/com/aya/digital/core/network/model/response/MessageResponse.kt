package com.aya.digital.core.network.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageResponse(
    val message: String?
)