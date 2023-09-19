package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.createschedule.telemed.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class SchedulerCreateScheduleIsTelemedUIModel(
  val isTelemed:Boolean
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is SchedulerCreateScheduleIsTelemedUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is SchedulerCreateScheduleIsTelemedUIModel
                && newItem.isTelemed == this.isTelemed
}