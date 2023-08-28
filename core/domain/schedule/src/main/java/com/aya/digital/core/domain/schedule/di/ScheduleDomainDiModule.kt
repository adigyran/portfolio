package com.aya.digital.core.domain.schedule.di

import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdByDateUseCase
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.base.impl.GetLatestScheduleByDoctorIdByDateUseCaseImpl
import com.aya.digital.core.domain.schedule.base.impl.GetLatestScheduleByDoctorIdUseCaseImpl
import com.aya.digital.core.domain.schedule.doctor.scheduler.GetDaySlotsScheduleUseCase
import com.aya.digital.core.domain.schedule.doctor.scheduler.GetMonthScheduleUseCase
import com.aya.digital.core.domain.schedule.doctor.scheduler.impl.GetDaySlotsScheduleUseCaseImpl
import com.aya.digital.core.domain.schedule.doctor.scheduler.impl.GetMonthScheduleUseCaseImpl
import com.aya.digital.core.domain.schedule.patient.selectable.GetSelectableScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.patient.selectable.impl.GetSelectableScheduleByDoctorIdUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun scheduleDomainDiModule() = DI.Module("scheduleDomainDiModule") {
    bind<GetLatestScheduleByDoctorIdUseCase>() with singleton {
        GetLatestScheduleByDoctorIdUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<GetLatestScheduleByDoctorIdByDateUseCase>() with singleton {
        GetLatestScheduleByDoctorIdByDateUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<GetSelectableScheduleByDoctorIdUseCase>() with singleton {
        GetSelectableScheduleByDoctorIdUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetMonthScheduleUseCase>() with singleton { GetMonthScheduleUseCaseImpl() }
    bind<GetDaySlotsScheduleUseCase>() with singleton {
        GetDaySlotsScheduleUseCaseImpl(
            instance(),
            instance(),
            instance()
        )
    }

}