package com.aya.digital.feature.tabs.doctorsearch.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.tabs.doctorsearch.navigation.DoctorSearchTabCoordinator
import com.aya.digital.feature.tabs.doctorsearch.viewmodel.DoctorSearchTabViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.kodein.di.*

fun doctorSearchTabDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("doctorSearchTabDiModule") {

    bind<Coordinator>(overrides = true) {
        scoped(CustomFragmentScope).singleton {
            DoctorSearchTabCoordinator(
                instance<Cicerone<Router>>().router,
                parentCoordinatorEvent,
                instance(),
                instance("doctors_search_tab_navigation")
            )
        }
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorSearchTabViewModel(parentCoordinatorEvent)
        }
    }
}