package com.aya.digital.feature.tabs.appointments.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.tabs.appointments.navigation.AppointmentsTabCoordinator
import com.aya.digital.feature.tabs.appointments.viewmodel.AppointmentsTabViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.kodein.di.*

fun appointmentsTabDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("appointmentsTabDiModule") {

    bind<Coordinator>(overrides = true) {
        scoped(CustomFragmentScope).singleton {
            AppointmentsTabCoordinator(
                instance<Cicerone<Router>>().router,
                parentCoordinatorEvent,
                instance(),
                instance("auth_navigation")
            )
        }
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            AppointmentsTabViewModel(parentCoordinatorEvent)
        }
    }
}