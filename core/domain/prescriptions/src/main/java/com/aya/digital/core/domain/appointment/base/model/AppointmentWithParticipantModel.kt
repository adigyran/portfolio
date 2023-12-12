package com.aya.digital.core.domain.appointment.base.model

import com.aya.digital.core.domain.appointment.participants.model.AppointmentParticipant
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.base.models.patients.PatientModel

data class AppointmentWithParticipantModel(
    val appointmentModel: AppointmentModel,
    val appointmentParticipant: AppointmentParticipant?=null
)
