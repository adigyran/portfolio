package com.aya.digital.core.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDateTime

@JsonClass(generateAdapter = true)
data class AppointmentResponse(
    val id: Int,
    val comment: String?,
    val createdAt: Any?,
    val description: Any?,
    val endDate: LocalDateTime,
    val minutesDuration: Int?,
    val address: String?,
    val priority: Int?,
    val startDate: LocalDateTime?,
    val appointmentStatus: String?,
    @Json(name = "refPatient") val patient: com.aya.digital.core.network.model.response.PatientResponse?,
    @Json(name = "refPractitioner") val practitioner: com.aya.digital.core.network.model.response.PractitionerResponse?,
    @Json(name = "refSlot") val slot: com.aya.digital.core.network.model.response.SlotResponse?,
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
