package com.aya.digital.core.feature.videocall.videocallscreen.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.videocall.videocallscreen.ui.VideoCallScreenView
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.StatusesTextsManager
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.VideoCallScreenStateTransformer
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun videoCallScreenDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: VideoCallScreenView.Param
) = DI.Module("videoCallScreenDiModule") {

    bind<VideoCallScreenStateTransformer>() with singleton {
        VideoCallScreenStateTransformer(
            instance(),
            instance(),
            instance()
        )
    }

    bind<StatusesTextsManager>() with singleton {
        StatusesTextsManager(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            VideoCallScreenViewModel(parentCoordinatorEvent, param,instance(),instance(),instance(),instance())
        }
    }
}