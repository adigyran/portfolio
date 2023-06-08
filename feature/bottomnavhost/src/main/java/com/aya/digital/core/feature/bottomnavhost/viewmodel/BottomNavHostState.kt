package com.aya.digital.core.feature.bottomnavhost.viewmodel

import android.os.Parcelable
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class BottomNavHostState(val avatarUrl:String? = null) : BaseState
