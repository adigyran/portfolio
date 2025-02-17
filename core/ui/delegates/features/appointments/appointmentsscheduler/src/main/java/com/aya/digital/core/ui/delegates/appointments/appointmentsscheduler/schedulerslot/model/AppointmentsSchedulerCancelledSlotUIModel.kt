package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import java.time.LocalDateTime

data class AppointmentsSchedulerCancelledSlotUIModel(
   override val id:Int,
   override val dateTime: LocalDateTime,
   override val timeText:String
) : AppointmentsSchedulerBaseSlotUIModel(id, dateTime, timeText) {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
       super.areItemsTheSame(newItem) && newItem is AppointmentsSchedulerCancelledSlotUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
       super.areContentsTheSame(newItem) && newItem is AppointmentsSchedulerCancelledSlotUIModel
}