package com.aya.digital.core.feature.tabviews.appointments.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.tabviews.appointments.ui.model.AppointmentsStateTransformer
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun appointmentsDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("appointmentsDiModule") {

    bind<AppointmentsStateTransformer>() with singleton { AppointmentsStateTransformer(instance(),instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            AppointmentsViewModel(parentCoordinatorEvent,instance())
        }
    }
}