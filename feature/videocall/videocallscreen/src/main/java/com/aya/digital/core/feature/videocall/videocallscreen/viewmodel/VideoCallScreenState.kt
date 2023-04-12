package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoCallScreenState(
    val roomToken:String?=null
) : BaseState
