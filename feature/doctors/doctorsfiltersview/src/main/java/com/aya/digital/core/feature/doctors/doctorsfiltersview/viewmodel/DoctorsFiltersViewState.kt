package com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorsFiltersViewState(
    val stub:Boolean = true
) : BaseState
