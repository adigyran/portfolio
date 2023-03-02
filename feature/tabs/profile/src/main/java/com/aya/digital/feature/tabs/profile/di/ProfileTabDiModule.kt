package com.aya.digital.feature.tabs.profile.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.tabs.profile.navigation.ProfileTabCoordinator
import com.aya.digital.feature.tabs.profile.viewmodel.ProfileTabViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.kodein.di.*

fun profileTabDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileTabDiModule") {

    bind<Coordinator>(overrides = true) {
        scoped(CustomFragmentScope).singleton {
            ProfileTabCoordinator(
                instance<Cicerone<Router>>().router,
                parentCoordinatorEvent,
                instance(),
                instance("profile_tab_navigation")
            )
        }
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileTabViewModel(parentCoordinatorEvent)
        }
    }
}