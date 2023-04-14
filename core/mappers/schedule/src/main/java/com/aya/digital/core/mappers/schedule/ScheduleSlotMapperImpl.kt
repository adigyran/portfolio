package com.aya.digital.core.mappers.schedule

import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import com.aya.digital.core.network.model.response.SlotResponse
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class ScheduleSlotMapperImpl : ScheduleSlotMapper() {
    override fun mapFrom(type: SlotResponse): ScheduleSlot =
        ScheduleSlot(
            id = type.id,
            startDate = Instant.parse(type.startDate).toLocalDateTime(TimeZone.currentSystemDefault()),
            endDate = Instant.parse(type.endDate).toLocalDateTime(TimeZone.currentSystemDefault()),
            statusSlot = type.statusSlot?:"",
            commentSlot = type.commentSlot?:"",
            overBooked = type.overbooked
        )
}