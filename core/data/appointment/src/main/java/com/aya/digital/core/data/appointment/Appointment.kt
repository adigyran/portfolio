package com.aya.digital.core.data.appointment

import kotlinx.datetime.LocalDateTime

data class Appointment(
    val id: Int,
    val comment: String?,
    val createdAt: LocalDateTime,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val minutesDuration: Int?,
    val participant: Participant?,
    val practitioner: Practitioner?,
    val status: Status,
    val type: String?,
    val telemedPreTime:Long?
)

enum class Status(val statusName:String) {
SCHEDULED("scheduled"),
CANCELLED("cancelled"),
DONE("done")
}
data class Participant(val id: Int, val firstName: String?, val lastName: String?)
