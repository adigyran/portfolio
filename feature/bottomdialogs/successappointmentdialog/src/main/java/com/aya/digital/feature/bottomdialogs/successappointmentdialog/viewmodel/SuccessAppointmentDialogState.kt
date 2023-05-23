package com.aya.digital.feature.bottomdialogs.successappointmentdialog.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SuccessAppointmentDialogState(
    val doctorName:String?=null,
    val dateTime: @RawValue LocalDateTime? = null
) : BaseState
