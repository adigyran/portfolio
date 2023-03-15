package com.aya.digital.core.feature.bottomnavhost.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.designsystem.views.navigation.bottom.HealthAppBottomNavigationView
import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostCoordinator
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigator
import com.aya.digital.core.feature.bottomnavhost.ui.model.BottomNavHostStateTransformer
import com.aya.digital.core.feature.bottomnavhost.viewmodel.BottomNavHostViewModel
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import org.kodein.di.*
import org.kodein.di.android.x.AndroidLifecycleScope

fun bottomNavHostModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("bottomNavHostModule") {

    bind<CoordinatorRouter>("parent_coordinator_bottomnav") with multiton {parentCoordinatorEvent}

    bind<BottomNavHostStateTransformer>() with singleton { BottomNavHostStateTransformer(instance()) }

    bind<Coordinator>(overrides = true) {
        scoped(CustomFragmentScope).singleton {
            BottomNavHostCoordinator(
                instance<Cicerone<Router>>().router,
                parentCoordinatorEvent,
                instance(),
                instance()
            )
        }
    }

    bind<BottomNavHostNavigator>() with factory { param: BottomNavHostNavigatorParam ->
        BottomNavHostNavigator(instance(),param.activity,param.fragment, param.navView,param.containerId, param.navListener, param.onExit)
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            BottomNavHostViewModel(parentCoordinatorEvent)
        }
    }
}

data class BottomNavHostNavigatorParam(
    val activity: FragmentActivity,
    val fragment: Fragment,
    val navView: HealthAppBottomNavigationView,
    val containerId : Int,
    val navListener: NavigationBarView.OnItemSelectedListener,
    val onExit: () -> Unit
)

