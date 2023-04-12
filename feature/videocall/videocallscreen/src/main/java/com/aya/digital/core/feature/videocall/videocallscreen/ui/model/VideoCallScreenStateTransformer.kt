package com.aya.digital.core.feature.videocall.videocallscreen.ui.model

import android.content.Context
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenState
import com.aya.digital.core.mvi.BaseStateTransformer

class VideoCallScreenStateTransformer(context: Context) :
    BaseStateTransformer<VideoCallScreenState, VideoCallScreenUiModel>() {
    override fun invoke(state: VideoCallScreenState): VideoCallScreenUiModel =
        VideoCallScreenUiModel(
           roomToken = state.roomToken
        )


}