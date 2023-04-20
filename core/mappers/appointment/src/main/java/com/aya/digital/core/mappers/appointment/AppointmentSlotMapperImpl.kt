package com.aya.digital.core.mappers.impl.appointment

import com.aya.digital.core.data.appointment.AppoinmentSlot
import com.aya.digital.core.data.appointment.mappers.AppointmentSlotMapper
import com.aya.digital.core.network.model.response.schedule.SlotResponse

internal class AppointmentSlotMapperImpl : AppointmentSlotMapper() {
    override fun mapFrom(type: SlotResponse): AppoinmentSlot {
        TODO("Not yet implemented")
    }
}