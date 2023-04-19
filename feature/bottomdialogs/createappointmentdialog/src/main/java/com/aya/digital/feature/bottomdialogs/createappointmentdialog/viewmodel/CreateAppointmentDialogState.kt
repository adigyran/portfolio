package com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel

import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CreateAppointmentDialogState(
    val slots: List<ScheduleSlotModel>? = null,
    val date: @RawValue LocalDate? = null,
    val selectedSlotId: Int? = null,
    val comment: String? = null
) : BaseState
