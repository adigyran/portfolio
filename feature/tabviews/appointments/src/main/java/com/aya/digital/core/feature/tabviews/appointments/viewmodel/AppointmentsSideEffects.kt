package com.aya.digital.core.feature.tabviews.appointments.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class AppointmentsSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : AppointmentsSideEffects()
}
