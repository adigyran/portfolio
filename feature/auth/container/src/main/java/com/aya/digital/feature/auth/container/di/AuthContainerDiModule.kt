package com.aya.digital.feature.auth.container.di

import androidx.fragment.app.Fragment
import com.aya.digital.core.dibase.scopes.CustomActivityScope
import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.auth.container.navigation.AuthContainerCoordinator
import com.aya.digital.feature.auth.container.viewmodel.AuthContainerViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kodein.di.*

fun authContainerDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("authContainerDiModule") {

    bind<Coordinator>(overrides = true) {
        scoped(CustomFragmentScope).singleton {
            AuthContainerCoordinator(
                instance<Cicerone<Router>>().router,
                parentCoordinatorEvent,
                instance(),
                instance("auth_navigation")
            )
        }
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            AuthContainerViewModel(parentCoordinatorEvent)
        }
    }
}