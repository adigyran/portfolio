package com.aya.digital.core.feature.bottomnavhost.di

import androidx.fragment.app.Fragment
import com.aya.digital.core.designsystem.views.navigation.bottom.HealthAppBottomNavigationView
import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostCoordinator
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigator
import com.aya.digital.core.feature.bottomnavhost.viewmodel.BottomNavHostViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import org.kodein.di.*

fun bottomNavHostModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("BottomDialogDiModule") {

    bind {
        scoped(CustomFragmentScope).singleton {
            BottomNavHostCoordinator(
                instance<Cicerone<Router>>().router,
                parentCoordinatorEvent
            )
        }
    }
    bind<BottomNavHostNavigator>() with factory { param: BottomNavHostNavigatorParam ->
        BottomNavHostNavigator(param.fragment, param.navView, param.navListener, param.onExit)
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            BottomNavHostViewModel(parentCoordinatorEvent)
        }
    }
}

data class BottomNavHostNavigatorParam(
    val fragment: Fragment,
    val navView: HealthAppBottomNavigationView,
    val navListener: NavigationBarView.OnItemSelectedListener,
    val onExit: () -> Unit
)

