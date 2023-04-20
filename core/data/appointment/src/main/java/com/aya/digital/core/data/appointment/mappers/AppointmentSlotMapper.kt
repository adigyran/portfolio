package com.aya.digital.core.data.appointment.mappers

import com.aya.digital.core.data.appointment.AppoinmentSlot
import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.network.model.response.schedule.SlotResponse

abstract class AppointmentSlotMapper : EntityMapper<SlotResponse, AppoinmentSlot>