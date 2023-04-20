package com.aya.digital.core.data.schedule.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.network.model.response.schedule.SlotResponse

abstract class ScheduleSlotMapper :
    EntityMapper<SlotResponse, ScheduleSlot>