package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import java.time.LocalDateTime

data class AppointmentsSchedulerAppointmentSlotUIModel(
    override val id: Int,
    override val dateTime: LocalDateTime,
    override val timeText: String,
    val appointmentUi: AppointmentUi
) : AppointmentsSchedulerBaseSlotUIModel(id, dateTime, timeText) {


    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is AppointmentsSchedulerAppointmentSlotUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is AppointmentsSchedulerAppointmentSlotUIModel
                && newItem.appointmentUi == this.appointmentUi

}
