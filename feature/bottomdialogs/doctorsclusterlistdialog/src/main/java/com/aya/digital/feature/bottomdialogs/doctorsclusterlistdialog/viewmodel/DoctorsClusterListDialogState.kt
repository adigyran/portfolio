package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.viewmodel

import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class DoctorsClusterListDialogState(
    val doctors: List<DoctorModel>? = null,
    val favoriteDoctors: List<Int>? = null
) : BaseState
