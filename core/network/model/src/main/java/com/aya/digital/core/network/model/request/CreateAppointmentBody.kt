package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateAppointmentBody(
    var slotId: Int?,
    var comment: String?
)