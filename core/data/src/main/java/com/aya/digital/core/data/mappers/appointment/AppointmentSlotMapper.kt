package com.aya.digital.core.data.mappers.appointment

import com.aya.digital.core.data.model.appointment.AppoinmentSlot
import com.aya.digital.core.network.model.response.SlotResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class AppointmentSlotMapper : EntityMapper<SlotResponse, AppoinmentSlot>