package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerday.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import java.time.LocalDate

data class AppointmentsSchedulerDayUIModel(
    val day: LocalDate,
    val dayOfWeekName: String,
    val dayOfMonthText: String,
    val hasSlots:Boolean,
    val selected:Boolean,
    val hasAppointments:Boolean
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is AppointmentsSchedulerDayUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is AppointmentsSchedulerDayUIModel
                && newItem.day == this.day
                && newItem.dayOfWeekName == this.dayOfWeekName
                && newItem.dayOfMonthText == this.dayOfMonthText
                && newItem.hasSlots == this.hasSlots
                && newItem.selected == this.selected
                && newItem.hasAppointments == this.hasAppointments
}