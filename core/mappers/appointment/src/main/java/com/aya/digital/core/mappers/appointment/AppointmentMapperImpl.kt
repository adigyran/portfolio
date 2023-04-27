package com.aya.digital.core.mappers.appointment

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.Participant
import com.aya.digital.core.data.appointment.mappers.AppointmentMapper
import com.aya.digital.core.network.model.response.AppointmentResponse
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class AppointmentMapperImpl : AppointmentMapper() {
    override fun mapFrom(type: AppointmentResponse): Appointment  =
        Appointment(
            id = type.id,
            comment = type.comment,
            createdAt = Instant.parse(type.createdAt).toLocalDateTime(TimeZone.currentSystemDefault()),
            startDate = Instant.parse(type.startDate).toLocalDateTime(TimeZone.currentSystemDefault()),
            endDate = Instant.parse(type.endDate).toLocalDateTime(TimeZone.currentSystemDefault()),
            minutesDuration = type.minutesDurations,
            participant = type.participant?.let { Participant(it.id,it.firstname,it.lastname) },
            type = type.type
        )
}