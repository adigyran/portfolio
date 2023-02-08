package com.aya.digital.feature.rootcontainer.di

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.dibase.scopes.CustomActivityScope
import com.aya.digital.feature.rootcontainer.navigation.RootCoordinator
import com.aya.digital.feature.rootcontainer.navigation.RootNavigator
import com.aya.digital.feature.rootcontainer.viewmodel.RootContainerViewModel
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.kodein.di.DI
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.bind
import org.kodein.di.multiton
import org.kodein.di.scoped
import org.kodein.di.instance
import org.kodein.di.factory
import org.kodein.di.singleton


fun rootContainerDiModule() = DI.Module("rootContainerDiModule") {

    bind<Coordinator> {
        scoped(
            AndroidLifecycleScope.multiItem
        ).multiton { fragmentManager: FragmentManager ->
            RootCoordinator(
                instance<Cicerone<Router>>().router,
                instance(),
                fragmentManager,
                instance("root_navigation")
            )
        }
    }

    bind<RootNavigator>() with factory { param: RootNavigatorParam ->
        RootNavigator(param.activity, param.resId, instance("root_navigation"))
    }

    bind {
        scoped(CustomActivityScope).singleton {
            RootContainerViewModel(instance())
        }
    }

}

data class RootNavigatorParam(val activity: FragmentActivity, val resId: Int)
