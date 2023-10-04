package com.aya.digital.core.feature.videocall.videocallscreen.ui.model

import android.app.RemoteAction
import com.aya.digital.core.mvi.BaseUiModel

data class VideoCallScreenUiModel(
  val roomToken:String?,
  val connectButtonIcn:Int,
  val localVideoButtonIcn:Int,
  val cameraSwitchVisible:Boolean,
  val localAudioButtonIcn:Int,
  val localVideoEnabled:Boolean,
  val localAudioEnabled:Boolean,
  val remoteActions:List<RemoteAction>,
  val uiVisibility:Boolean
) : BaseUiModel
