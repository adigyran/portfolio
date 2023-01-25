package com.aya.digital.core.feature.container.di

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.dibase.scopes.CustomActivityScope
import com.aya.digital.core.feature.container.navigation.RootCoordinator
import com.aya.digital.core.feature.container.navigation.RootNavigator
import com.aya.digital.core.feature.container.viewmodel.RootContainerViewModel
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.kodein.di.*
import org.kodein.di.android.x.AndroidLifecycleScope

fun rootContainerDiModule() = DI.Module("rootContainerDiModule") {

    bind<Coordinator> {
        scoped(
            AndroidLifecycleScope.multiItem
        ).multiton { fragmentManager: FragmentManager ->
            RootCoordinator(
                instance<Cicerone<Router>>().router,
                instance(),
                fragmentManager
            )
        }
    }

    bind<RootNavigator>() with factory { param: RootNavigatorParam ->
        RootNavigator(param.activity, param.resId)
    }

    bind {
        scoped(CustomActivityScope).singleton {
            RootContainerViewModel()
        }
    }

}

data class RootNavigatorParam(val activity: FragmentActivity, val resId: Int)
