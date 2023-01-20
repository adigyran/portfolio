package com.aya.digital.core.mappers.impl.appointment

import com.aya.digital.core.data.mappers.appointment.AppointmentMapper
import com.aya.digital.core.data.mappers.appointment.AppointmentSlotMapper
import com.aya.digital.core.data.mappers.appointment.PatientMapper
import com.aya.digital.core.data.mappers.appointment.PractitionerMapper
import com.aya.digital.core.data.model.appointment.Appointment
import com.aya.digital.core.network.model.response.AppointmentResponse

class AppointmentMapperImpl(
    private val patientMapper: PatientMapper,
    private val practitionerMapper: PractitionerMapper,
    private val appointmentSlotMapper: AppointmentSlotMapper
) : AppointmentMapper() {
    override fun mapFrom(type: AppointmentResponse): Appointment {
        TODO("Not yet implemented")
    }
}