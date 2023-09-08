package com.aya.digital.core.mappers.schedule

import com.aya.digital.core.data.schedule.Schedule
import com.aya.digital.core.data.schedule.mappers.ScheduleMapper
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import com.aya.digital.core.network.model.response.schedule.ScheduleResponse
import java.time.Instant
import java.time.ZoneId


internal class ScheduleMapperImpl(private val slotMapper: ScheduleSlotMapper) : ScheduleMapper() {
    override fun mapFrom(type: ScheduleResponse): Schedule =
        Schedule(
            id = type.id,
            dateTime = Instant.parse(type.startDate).atZone(ZoneId.systemDefault()).toLocalDateTime()
        )
}