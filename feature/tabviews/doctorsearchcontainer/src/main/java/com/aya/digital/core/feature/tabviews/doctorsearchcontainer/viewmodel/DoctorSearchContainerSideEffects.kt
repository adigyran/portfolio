package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class DoctorSearchContainerSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : DoctorSearchContainerSideEffects()
}
