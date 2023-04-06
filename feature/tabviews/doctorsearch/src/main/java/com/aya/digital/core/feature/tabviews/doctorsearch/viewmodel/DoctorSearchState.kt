package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorSearchState(val name:String) : BaseState
