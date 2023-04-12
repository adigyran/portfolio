package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.feature.videocall.videocallscreen.ui.VideoCallScreenView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class VideoCallScreenViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: VideoCallScreenView.Param
) :
    BaseViewModel<VideoCallScreenState, BaseSideEffect>() {
    override val container = container<VideoCallScreenState, BaseSideEffect>(
        initialState = VideoCallScreenState(),
    )
    {
    }

}

