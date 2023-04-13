package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class VideoCallScreenSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : VideoCallScreenSideEffects()
    data class ConnectToRoom(val roomId:String, val roomToken:String) : VideoCallScreenSideEffects()
}
