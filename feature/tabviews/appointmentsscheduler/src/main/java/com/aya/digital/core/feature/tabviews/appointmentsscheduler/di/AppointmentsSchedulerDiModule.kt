package com.aya.digital.core.feature.tabviews.appointmentsscheduler.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui.model.AppointmentsSchedulerStateTransformer
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.AppointmentsSchedulerViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun appointmentsSchedulerDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("appointmentsSchedulerDiModule") {

    bind<AppointmentsSchedulerStateTransformer>() with singleton {
        AppointmentsSchedulerStateTransformer(
            context = instance(),
            dateTimeUtils = instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            AppointmentsSchedulerViewModel(
                coordinatorRouter = parentCoordinatorEvent,
                rootCoordinatorRouter = instance("parent_coordinator_bottomnav"),
                getProfileBriefUseCase = instance(),
                getMonthScheduleUseCase = instance(),
                getDaySlotsScheduleUseCase = instance(),
                getAppointmentsUseCase = instance(),
                getAppointmentsForDayUseCase = instance()
            )
        }
    }
}