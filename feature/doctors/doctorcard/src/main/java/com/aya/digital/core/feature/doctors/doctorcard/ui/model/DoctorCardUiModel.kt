package com.aya.digital.core.feature.doctors.doctorcard.ui.model

import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorCardUiModel(
    val doctorCardMode: DoctorCardMode = DoctorCardMode.ShowingSlots,
    val doctorName: String? = null,
    val doctorSpeciality: String? = null,
    val doctorAvatar: String? = null,
    val doctorClinic: String? = null,
    val doctorAddress: String? = null,
    val data: List<DiffItem>? = null,
    val bioReadMore: Boolean = false,
    val isFavorite:Boolean = false
) : BaseUiModel
