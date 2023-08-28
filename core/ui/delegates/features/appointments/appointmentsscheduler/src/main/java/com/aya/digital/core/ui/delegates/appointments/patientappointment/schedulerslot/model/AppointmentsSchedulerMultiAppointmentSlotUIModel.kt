package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import java.time.LocalDate
import java.time.LocalDateTime

data class AppointmentsSchedulerMultiAppointmentSlotUIModel(
    override val id: Int,
    override val dateTime: LocalDateTime,
    override val timeText: String,
    val appointmentCountText:String
) : AppointmentsSchedulerBaseSlotUIModel(id, dateTime, timeText) {


    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is AppointmentsSchedulerMultiAppointmentSlotUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is AppointmentsSchedulerMultiAppointmentSlotUIModel
                && newItem.appointmentCountText == this.appointmentCountText

}
