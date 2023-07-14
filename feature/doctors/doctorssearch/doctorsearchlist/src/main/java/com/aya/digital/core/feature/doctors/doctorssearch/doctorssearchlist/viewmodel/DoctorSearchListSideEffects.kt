package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class DoctorSearchListSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : DoctorSearchListSideEffects()
}
