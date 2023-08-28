package com.aya.digital.core.mappers.appointment

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.Participant
import com.aya.digital.core.data.appointment.Practitioner
import com.aya.digital.core.data.appointment.Status
import com.aya.digital.core.data.appointment.mappers.AppointmentMapper
import com.aya.digital.core.network.model.response.AppointmentResponse
import java.time.Instant

import java.time.ZoneId

internal class AppointmentMapperImpl : AppointmentMapper() {
    override fun mapFrom(type: AppointmentResponse): Appointment =
        Appointment(
            id = type.id,
            comment = type.comment,
            createdAt = type.createdAt.parseDate(),
            startDate = type.startDate.parseDate(),
            endDate = type.endDate.parseDate(),
            minutesDuration = type.minutesDurations,
            participant = type.participant?.let { Participant(it.id, it.firstname, it.lastname) },
            practitioner = type.practitioner?.let { Practitioner(it.id) },
            status = Status.values().find { it.statusName == type.status } ?: Status.CANCELLED,
            type = type.type,
            telemedPreTime = type.telemedPreTime,
            slotId = type.slotId
        )

    private fun String.parseDate() = Instant.parse(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
}