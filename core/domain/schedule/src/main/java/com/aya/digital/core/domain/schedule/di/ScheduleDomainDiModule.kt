package com.aya.digital.core.domain.schedule.di

import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdByDateUseCase
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.base.impl.GetLatestScheduleByDoctorIdByDateUseCaseImpl
import com.aya.digital.core.domain.schedule.base.impl.GetLatestScheduleByDoctorIdUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun scheduleDomainDiModule() = DI.Module("scheduleDomainDiModule") {
    bind<GetLatestScheduleByDoctorIdUseCase>() with singleton { GetLatestScheduleByDoctorIdUseCaseImpl(instance()) }
    bind<GetLatestScheduleByDoctorIdByDateUseCase>() with singleton { GetLatestScheduleByDoctorIdByDateUseCaseImpl(instance()) }


}