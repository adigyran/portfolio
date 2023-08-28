package com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem
import java.time.LocalDate

data class AppointmentsSchedulerUiModel(
    val monthName:String?=null,
    val daysData: List<DiffItem>? = null,
    val slotsData: List<DiffItem>? = null,
    val selectedDayDate:LocalDate? = null
) : BaseUiModel
