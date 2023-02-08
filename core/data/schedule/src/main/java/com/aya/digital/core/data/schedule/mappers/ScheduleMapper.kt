package com.aya.digital.core.data.schedule.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.schedule.Schedule
import com.aya.digital.core.network.model.response.schedule.ScheduleResponse

abstract class ScheduleMapper :
    EntityMapper<ScheduleResponse, Schedule>