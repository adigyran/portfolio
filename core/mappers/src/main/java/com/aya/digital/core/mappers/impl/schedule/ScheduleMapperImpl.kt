package com.aya.digital.core.mappers.impl.schedule

import com.aya.digital.core.data.mappers.schedule.ScheduleMapper
import com.aya.digital.core.data.mappers.schedule.ScheduleSlotMapper
import com.aya.digital.core.data.model.schedule.Schedule
import com.aya.digital.core.network.model.response.schedule.ScheduleResponse

internal class ScheduleMapperImpl(private val slotMapper: ScheduleSlotMapper) : ScheduleMapper() {
    override fun mapFrom(type: ScheduleResponse): Schedule {
        TODO("Not yet implemented")
    }
}