package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorCardState(
    val doctorCardMode: DoctorCardMode = DoctorCardMode.ShowingSlots,
    val doctorName:String? = null,
    val doctorSpeciality:String? = null,
    val doctorAvatar:String? = null,
    val doctorClinic:String? = null,
    val doctorAddress:String? = null
) : BaseState
