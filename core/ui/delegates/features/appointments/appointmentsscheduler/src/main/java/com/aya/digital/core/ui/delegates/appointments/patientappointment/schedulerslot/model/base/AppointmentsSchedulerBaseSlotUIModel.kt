package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.base

import com.aya.digital.core.ui.adapters.base.DiffItem
import java.time.LocalDateTime

abstract class AppointmentsSchedulerBaseSlotUIModel(
    open val id:Int,
    open val dateTime: LocalDateTime,
    open val timeText:String
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is AppointmentsSchedulerBaseSlotUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is AppointmentsSchedulerBaseSlotUIModel
                && newItem.id == this.id
                && newItem.dateTime == this.dateTime
                && newItem.timeText == this.timeText
}