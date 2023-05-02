package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel

import com.aya.digital.core.domain.doctors.base.model.DoctorModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorSearchState(val doctors:List<DoctorModel>? = null) : BaseState
