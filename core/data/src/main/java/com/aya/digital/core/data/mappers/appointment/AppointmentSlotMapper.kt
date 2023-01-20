package com.aya.digital.core.data.mappers.appointment

import com.aya.digital.core.data.model.appointment.AppoinmentSlot
import com.aya.digital.core.data.model.appointment.Appointment
import com.aya.digital.core.data.model.schedule.Schedule
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.SlotResponse
import com.aya.digital.core.network.model.response.schedule.ScheduleResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class AppointmentSlotMapper : EntityMapper<SlotResponse, AppoinmentSlot>