package com.aya.digital.core.mappers.appointment

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.mappers.AppointmentMapper
import com.aya.digital.core.network.model.response.AppointmentResponse

internal class AppointmentMapperImpl : AppointmentMapper() {
    override fun mapFrom(type: AppointmentResponse): Appointment {
        TODO("Not yet implemented")
    }
}