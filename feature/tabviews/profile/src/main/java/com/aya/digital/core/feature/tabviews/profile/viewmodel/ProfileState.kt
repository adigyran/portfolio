package com.aya.digital.core.feature.tabviews.profile.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileState(val name:String) : BaseState
