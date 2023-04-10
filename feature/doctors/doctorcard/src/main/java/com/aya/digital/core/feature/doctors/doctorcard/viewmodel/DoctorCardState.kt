package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorCardState(
    val doctorName:String? = null
) : BaseState
