package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class AppointmentsSchedulerEmptyDayUIModel(
   val doctorName:String
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
     newItem is AppointmentsSchedulerEmptyDayUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
       newItem is AppointmentsSchedulerEmptyDayUIModel
               && newItem.doctorName == this.doctorName
}