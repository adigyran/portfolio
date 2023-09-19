package com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class CreateScheduleDialogSideEffects:BaseSideEffect {
    data class ShowStartTimePicker(val date:LocalDate, val startTime: LocalTime?):CreateScheduleDialogSideEffects()
    data class ShowEndTimePicker(val date:LocalDate, val endTime: LocalTime?):CreateScheduleDialogSideEffects()
    data class ShowTimePicker(val tag:Int,val date:LocalDate, val selectedTime: LocalTime?):CreateScheduleDialogSideEffects()
    data class Error(val error:BaseViewModel.ErrorSideEffect) : CreateScheduleDialogSideEffects()
}
