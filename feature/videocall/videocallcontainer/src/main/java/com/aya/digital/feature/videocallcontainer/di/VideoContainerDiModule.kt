package com.aya.digital.feature.videocallcontainer.di

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.dibase.scopes.CustomActivityScope
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerCoordinator
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerNavigator
import com.aya.digital.feature.videocallcontainer.viewmodel.VideoContainerViewModel
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.feature.videocallcontainer.ui.VideoContainerView
import com.aya.digital.feature.videocallcontainer.ui.model.VideoContainerStateTransformer
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


fun videoContainerDiModule(param: VideoContainerView.Param) = DI.Module("videoContainerDiModule") {

    bind<VideoContainerStateTransformer>() with singleton {
        VideoContainerStateTransformer(
            instance())
    }
    bind<Coordinator> {
        scoped(
            AndroidLifecycleScope.multiItem
        ).multiton { fragmentManager: FragmentManager ->
            VideoContainerCoordinator(
                instance<Cicerone<Router>>().router,
                instance(),
                fragmentManager,
                instance("videocall_navigation")
            )
        }
    }

    bind<VideoContainerNavigator>() with factory { param: RootNavigatorParam ->
        VideoContainerNavigator(param.activity, param.resId, instance("videocall_navigation"))
    }

    bind {
        scoped(CustomActivityScope).singleton {
            VideoContainerViewModel(param,instance(),instance(),instance(),instance())
        }
    }

}

data class RootNavigatorParam(val activity: FragmentActivity, val resId: Int)
