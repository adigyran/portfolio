package com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class AppointmentsSchedulerSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : AppointmentsSchedulerSideEffects()
}
