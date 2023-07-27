package com.aya.digital.core.repository.schedule

import com.aya.digital.core.data.schedule.repository.ScheduleRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun scheduleRepositoryDiModule() = DI.Module("scheduleRepositoryDiModule") {
    bind<ScheduleRepository>() with singleton { ScheduleRepositoryImpl(instance(),instance(),instance()) }
}