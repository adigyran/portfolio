package com.aya.digital.core.data.mappers.appointment

import com.aya.digital.core.data.model.appointment.Appointment
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class AppointmentMapper : EntityMapper<AppointmentResponse, Appointment>