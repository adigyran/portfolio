package com.aya.digital.core.feature.appointments.appointmentcard.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import kotlinx.datetime.LocalDate
import kotlin.time.Duration

sealed class AppointmentCardSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : AppointmentCardSideEffects()
    data class ShowCustomDateDialog(val selectableDates:List<LocalDate>) : AppointmentCardSideEffects()

    data class ShowTelemedicineNotReadyDialog(val duration: Duration):AppointmentCardSideEffects()
}
