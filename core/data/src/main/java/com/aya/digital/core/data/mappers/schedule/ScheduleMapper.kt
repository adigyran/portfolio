package com.aya.digital.core.data.mappers.schedule

import com.aya.digital.core.data.model.schedule.Schedule
import com.aya.digital.core.network.model.response.schedule.ScheduleResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class ScheduleMapper : EntityMapper<ScheduleResponse, Schedule>