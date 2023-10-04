package com.aya.digital.feature.videocallcontainer.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoContainerState(val inProgress:Boolean = false) : BaseState
