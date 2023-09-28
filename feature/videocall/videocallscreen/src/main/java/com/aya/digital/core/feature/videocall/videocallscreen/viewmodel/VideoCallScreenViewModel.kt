package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthRoomTokenUseCase
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.ui.VideoCallScreenView
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
        initialiseState()
    }

    private fun initialiseState() = intent {
        reduce {
            state.copy(
                localAudioEnabled = false,
                localVideoEnabled = false,
                isConnected = false
            )
        }
    }



    fun toggleConnectionClicked() = intent {
        when (state.isConnected) {
            true -> showDisconnectDialog()
            false -> connect()
        }
    }

    fun resumeOngoingConnection() = intent {
        if(state.isConnected) connect()
    }
    private fun connect() = intent {
        getRoomToken()
    }

    private fun showDisconnectDialog() = intent {
        postSideEffect(VideoCallScreenSideEffects.ShowDisconnectDialog)
    }

    fun onDisconnectConfirmed() = intent {
        disconnect()
        exit()
    }

    private fun disconnect() = intent {
        reduce { state.copy(isConnected = false) }
    }

    private fun exit()
    {
        coordinatorRouter.sendEvent(VideoCallScreenNavigationEvents.Back)

    }

    fun toggleLocalVideoClicked() = intent {
        reduce { state.copy(localVideoEnabled = !state.localVideoEnabled) }
    }

    fun toggleLocalAudioClicked() = intent {
        reduce { state.copy(localAudioEnabled = !state.localAudioEnabled) }
    }

    fun onSuccessfulConnection() = intent {
        reduce { state.copy(isConnected = true) }
    }

    fun onConnectionFailure() = intent {
        reduce { state.copy(isConnected = false) }
        initialiseState()
    }

    private fun getRoomToken() = intent {
        getTeleHealthRoomTokenUseCase(param.roomId)
            .await()
            .processResult({ token ->
                reduce { state.copy(roomToken = token) }
                postSideEffect(VideoCallScreenSideEffects.ConnectToRoom("${param.roomId}", token))
            }, { processError(it) })
    }

    override fun onCleared() {
        disconnect()
        super.onCleared()
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(VideoCallScreenSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}

