package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthRoomTokenUseCase
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.ui.VideoCallScreenView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class VideoCallScreenViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: VideoCallScreenView.Param,
    private val getTeleHealthRoomTokenUseCase: GetTeleHealthRoomTokenUseCase
) :
    BaseViewModel<VideoCallScreenState, VideoCallScreenSideEffects>() {
    override val container = container<VideoCallScreenState, VideoCallScreenSideEffects>(
        initialState = VideoCallScreenState(),
    )
    {
       // getRoomToken()
    }

    fun connectClicked() = intent {
        getRoomToken()
    }

    fun disconnectClicked() = intent {
        coordinatorRouter.sendEvent(VideoCallScreenNavigationEvents.Back)
    }

    private fun getRoomToken() = intent {
        getTeleHealthRoomTokenUseCase(param.roomId)
            .await()
            .processResult({token->
                           reduce { state.copy(roomToken = token) }
                           postSideEffect(VideoCallScreenSideEffects.ConnectToRoom("${param.roomId}",token))
            },{processError(it)})
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(VideoCallScreenSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

}

