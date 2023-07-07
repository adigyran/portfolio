package com.aya.digital.core.network.model.response.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationSettingsResponse(
    val isEmail: Boolean,
    val isPhone: Boolean
)
