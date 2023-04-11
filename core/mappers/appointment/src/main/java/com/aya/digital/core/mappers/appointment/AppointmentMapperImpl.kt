package com.aya.digital.core.mappers.appointment

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.mappers.AppointmentMapper
import com.aya.digital.core.network.model.response.AppointmentResponse
import kotlinx.datetime.LocalDateTime

internal class AppointmentMapperImpl : AppointmentMapper() {
    override fun mapFrom(type: AppointmentResponse): Appointment  =
        Appointment(
            id = type.id,
            comment = null,
            createdAt = LocalDateTime.parse(type.createdAt),
            startDate = LocalDateTime.parse(type.startDate),
            endDate = LocalDateTime.parse(type.endDate),
            minutesDuration = type.minutesDuration
        )
}