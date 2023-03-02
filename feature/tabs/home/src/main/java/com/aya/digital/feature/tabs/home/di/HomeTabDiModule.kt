package com.aya.digital.feature.tabs.home.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.tabs.home.navigation.HomeTabCoordinator
import com.aya.digital.feature.tabs.home.viewmodel.HomeTabViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.kodein.di.*

fun homeTabDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("homeTabDiModule") {

    bind<Coordinator>(overrides = true) {
        scoped(CustomFragmentScope).singleton {
            HomeTabCoordinator(
                instance<Cicerone<Router>>().router,
                parentCoordinatorEvent,
                instance(),
                instance("home_tab_navigation")
            )
        }
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            HomeTabViewModel(parentCoordinatorEvent)
        }
    }
}