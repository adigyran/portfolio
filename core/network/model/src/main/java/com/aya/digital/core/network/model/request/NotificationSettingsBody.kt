package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationSettingsBody(
    val isEmail: Boolean,
    val isPhone: Boolean
)
