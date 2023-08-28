package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import java.time.LocalDate
import java.time.LocalDateTime

data class AppointmentsSchedulerEmptySlotUIModel(
   override val id:Int,
   override val dateTime: LocalDateTime,
   override val timeText:String
) : AppointmentsSchedulerBaseSlotUIModel(id, dateTime, timeText) {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
       super.areItemsTheSame(newItem) && newItem is AppointmentsSchedulerEmptySlotUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
       super.areContentsTheSame(newItem) && newItem is AppointmentsSchedulerEmptySlotUIModel
}