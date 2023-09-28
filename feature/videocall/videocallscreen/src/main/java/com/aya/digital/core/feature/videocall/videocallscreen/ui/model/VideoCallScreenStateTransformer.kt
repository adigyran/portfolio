package com.aya.digital.core.feature.videocall.videocallscreen.ui.model

import android.content.Context
import com.aya.digital.core.feature.videocall.videocallscreen.R
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenState
import com.aya.digital.core.mvi.BaseStateTransformer

class VideoCallScreenStateTransformer(context: Context) :
    BaseStateTransformer<VideoCallScreenState, VideoCallScreenUiModel>() {
    override fun invoke(state: VideoCallScreenState): VideoCallScreenUiModel =
        VideoCallScreenUiModel(
            roomToken = state.roomToken,
            connectButtonIcn = state.isConnected.getConnectButtonIcn(),
            cameraSwitchVisible = state.localVideoEnabled,
            localAudioButtonIcn = state.localAudioEnabled.getLocalAudioButtonIcn(),
            localVideoButtonIcn = state.localVideoEnabled.getLocalVideoButtonIcn(),
            localVideoEnabled = state.localVideoEnabled,
            localAudioEnabled = state.localAudioEnabled
        )

    private fun Boolean.getConnectButtonIcn() = when (this) {
        true -> R.drawable.ic_call_end_white_24px
        false -> R.drawable.ic_video_call_white_24dp
    }

    private fun Boolean.getLocalAudioButtonIcn() = when (this) {
        true -> R.drawable.ic_mic_white_24dp
        false -> R.drawable.ic_mic_off_black_24dp
    }

    private fun Boolean.getLocalVideoButtonIcn() = when (this) {
        true -> R.drawable.ic_videocam_white_24dp
        false -> R.drawable.ic_videocam_off_black_24dp
    }
}