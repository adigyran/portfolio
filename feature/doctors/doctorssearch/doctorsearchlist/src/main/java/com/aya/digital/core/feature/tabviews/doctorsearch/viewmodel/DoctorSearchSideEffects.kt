package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class DoctorSearchSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : DoctorSearchSideEffects()
}
