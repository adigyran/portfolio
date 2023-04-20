package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import kotlinx.datetime.LocalDate

sealed class DoctorCardSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : DoctorCardSideEffects()
    data class ShowCustomDateDialog(val selectableDates:List<LocalDate>) : DoctorCardSideEffects()
}
