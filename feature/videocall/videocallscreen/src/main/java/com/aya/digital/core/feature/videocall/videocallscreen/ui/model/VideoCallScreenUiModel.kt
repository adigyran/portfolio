package com.aya.digital.core.feature.videocall.videocallscreen.ui.model

import com.aya.digital.core.mvi.BaseUiModel

data class VideoCallScreenUiModel(
  val roomToken:String?
) : BaseUiModel
