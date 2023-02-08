package com.aya.digital.core.mappers.schedule

import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import com.aya.digital.core.network.model.response.SlotResponse

internal class ScheduleSlotMapperImpl : ScheduleSlotMapper() {
    override fun mapFrom(type: SlotResponse): ScheduleSlot {
        TODO("Not yet implemented")
    }
}