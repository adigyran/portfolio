package com.aya.digital.feature.rootcontainer.viewmodel

import android.os.Parcelable
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class RootContainerState(val inProgress:Boolean = false) : BaseState
