package com.aya.digital.core.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDateTime

@JsonClass(generateAdapter = true)
data class AppointmentResponse(
    val id: Int,
    val createdAt: String,
    val endDate: String,
    val minutesDuration: Int,
    val startDate: String,
)

@JsonClass(generateAdapter = true)
data class PatientResponse(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val photo: String?
)

@JsonClass(generateAdapter = true)
data class PractitionerResponse(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val photo: String?,
    val speciality: String?
)

@JsonClass(generateAdapter = true)
data class SlotResponse(
    val id: Int?,
    val scheduleId: Int,
    val slotStart: LocalDateTime,
    val slotEnd: LocalDateTime,
    val type: String?,
    val comment: String?,
    val status: String,
    val date: LocalDateTime
)
