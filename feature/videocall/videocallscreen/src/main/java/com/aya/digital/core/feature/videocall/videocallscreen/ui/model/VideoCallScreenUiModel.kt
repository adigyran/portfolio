package com.aya.digital.core.feature.videocall.videocallscreen.ui.model

import android.app.RemoteAction
import com.aya.digital.core.mvi.BaseUiModel

data class VideoCallScreenUiModel(
  val roomToken:String?,
  val roomStatusText:String,
  val participantStatusText:String,
  val connectButtonIcn:Int,
  val connectButtonIcnTint:Int,
  val localVideoButtonIcn:Int,
  val cameraSwitchVisible:Boolean,
  val localAudioButtonIcn:Int,
  val localVideoEnabled:Boolean,
  val localAudioEnabled:Boolean,
  val remoteActions:List<RemoteAction>,
  val uiVisibility:Boolean,
  val participantConnected:Boolean,
  val durationTimerText:String
) : BaseUiModel
