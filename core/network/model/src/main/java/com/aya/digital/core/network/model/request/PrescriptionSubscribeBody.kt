package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class PrescriptionSubscribeBody(
    val practitionerId: Int,
    val patientId: Int,
    val appointmentId: Int
)
