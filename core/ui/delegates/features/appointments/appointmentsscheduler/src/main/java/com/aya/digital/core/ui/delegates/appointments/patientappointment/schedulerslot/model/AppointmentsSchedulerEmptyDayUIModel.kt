package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import java.time.LocalDate
import java.time.LocalDateTime

data class AppointmentsSchedulerEmptyDayUIModel(
   val doctorName:String
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
     newItem is AppointmentsSchedulerEmptyDayUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
       newItem is AppointmentsSchedulerEmptyDayUIModel
               && newItem.doctorName == this.doctorName
}