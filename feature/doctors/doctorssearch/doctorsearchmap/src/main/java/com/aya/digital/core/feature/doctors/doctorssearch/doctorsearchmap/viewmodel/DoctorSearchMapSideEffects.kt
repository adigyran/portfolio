package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class DoctorSearchMapSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : DoctorSearchMapSideEffects()
}
