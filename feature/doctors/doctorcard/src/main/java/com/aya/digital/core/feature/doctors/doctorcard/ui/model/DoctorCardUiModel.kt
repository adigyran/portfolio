package com.aya.digital.core.feature.doctors.doctorcard.ui.model

import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.mvi.BaseUiModel

data class DoctorCardUiModel(
    val doctorCardMode: DoctorCardMode = DoctorCardMode.ShowingSlots,
    val doctorName: String? = null,
    val doctorSpeciality: String? = null,
    val doctorAvatar: String? = null,
    val doctorClinic: String? = null,
    val doctorAddress: String? = null
) : BaseUiModel
