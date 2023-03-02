package com.aya.digital.core.navigation.di

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.dibase.scopes.CustomActivityScope
import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.CustomNavigator
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.navigation.coordinator.StubCoordinator
import com.aya.digital.core.navigation.events.invalidToken.InvalidTokenEventManager
import com.aya.digital.core.navigation.events.invalidToken.InvalidTokenEventManagerImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.kodein.di.*

fun navigationDiModule() = DI.Module("navigationDiModule") {
    bind<Cicerone<Router>>() { scoped(CustomFragmentScope).singleton { Cicerone.create() } }

    bind<Cicerone<Router>>() { scoped(CustomActivityScope).singleton { Cicerone.create() } }

    bind<CoordinatorHolder>() { scoped(CustomFragmentScope).singleton { CoordinatorHolder() } }

    bind<CoordinatorHolder>() { scoped(CustomActivityScope).singleton { CoordinatorHolder() } }

    bind<Coordinator>() {
        scoped(CustomFragmentScope).singleton { StubCoordinator() }
    }

    bind<Coordinator>() {
        scoped(CustomActivityScope).singleton { StubCoordinator() }
    }

    bind<CustomNavigator>() with factory { param: CustomNavigatorParam ->
        CustomNavigator(
            param.activity, param.containerId, param.fragmentManager,  param.onExit
        )
    }

    bind<InvalidTokenEventManager>() { singleton { InvalidTokenEventManagerImpl() }}
}

data class CustomNavigatorParam(
    val activity: FragmentActivity, val fragmentManager: FragmentManager, val containerId: Int,
    val onExit: () -> Unit,
)