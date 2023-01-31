package com.aya.digital.core.mappers.impl.schedule

import com.aya.digital.core.data.mappers.schedule.ScheduleSlotMapper
import com.aya.digital.core.data.model.appointment.AppoinmentSlot
import com.aya.digital.core.network.model.response.SlotResponse

internal class ScheduleSlotMapperImpl : ScheduleSlotMapper() {
    override fun mapFrom(type: SlotResponse): AppoinmentSlot {
        TODO("Not yet implemented")
    }
}