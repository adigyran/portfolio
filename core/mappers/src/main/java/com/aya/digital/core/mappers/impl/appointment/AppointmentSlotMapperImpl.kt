package com.aya.digital.core.mappers.impl.appointment

import com.aya.digital.core.data.mappers.appointment.AppointmentSlotMapper
import com.aya.digital.core.data.model.appointment.AppoinmentSlot
import com.aya.digital.core.network.model.response.SlotResponse

class AppointmentSlotMapperImpl : AppointmentSlotMapper() {
    override fun mapFrom(type: SlotResponse): AppoinmentSlot {
        TODO("Not yet implemented")
    }
}