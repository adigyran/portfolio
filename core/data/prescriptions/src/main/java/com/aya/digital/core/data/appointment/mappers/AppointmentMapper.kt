package com.aya.digital.core.data.appointment.mappers

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.network.model.response.AppointmentResponse

abstract class AppointmentMapper : EntityMapper<AppointmentResponse, Appointment>