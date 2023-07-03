package com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class DoctorsFiltersViewSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : DoctorsFiltersViewSideEffects()
}
