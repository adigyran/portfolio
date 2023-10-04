package com.aya.digital.feature.videocallcontainer.ui.model

import android.content.Context
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.feature.videocallcontainer.viewmodel.VideoContainerState

class VideoContainerStateTransformer(
    private val context: Context) :
    BaseStateTransformer<VideoContainerState, VideoContainerUiModel>() {
    override fun invoke(state: VideoContainerState): VideoContainerUiModel =
        VideoContainerUiModel(
           inProgress = state.inProgress
        )



}