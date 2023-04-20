package com.aya.digital.core.mappers.schedule

import com.aya.digital.core.data.schedule.mappers.ScheduleMapper
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun scheduleMappersDiModule() = DI.Module("scheduleMappersDiModule") {
    bind<ScheduleSlotMapper>() with singleton { ScheduleSlotMapperImpl() }
    bind<ScheduleMapper>() with singleton { ScheduleMapperImpl(instance()) }

}