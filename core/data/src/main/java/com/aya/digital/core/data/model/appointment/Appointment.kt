package com.aya.digital.core.data.model.appointment

import kotlinx.datetime.LocalDateTime

data class Appointment(
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
    val patient: Patient?,
    val practitioner: Practitioner?,
    val slot: AppoinmentSlot?,
)