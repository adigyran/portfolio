package com.aya.digital.core.mappers.schedule

import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import com.aya.digital.core.network.model.response.schedule.SlotResponse
import kotlinx.datetime.toLocalDateTime
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

internal class ScheduleSlotMapperImpl : ScheduleSlotMapper() {
    override fun mapFrom(type: SlotResponse): ScheduleSlot =
        ScheduleSlot(
            id = type.id,
            startDate = type.startDate.parseDate(),
            endDate = type.endDate.parseDate(),
            statusSlot = type.statusSlot ?: "",
            commentSlot = type.commentSlot ?: "",
            type = type.type ?: "",
            overBooked = type.overbooked
        )

    private fun String.parseDate() = Instant.parse(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
}