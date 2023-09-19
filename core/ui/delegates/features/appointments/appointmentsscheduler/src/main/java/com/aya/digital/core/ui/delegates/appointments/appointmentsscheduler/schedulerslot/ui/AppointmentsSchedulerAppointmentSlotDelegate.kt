package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.AppointmentsSchedulerAppointmentSlotUIModel
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.base.AppointmentsSchedulerSlotBaseDelegate
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.views.AppointmentView
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerSlotBinding

class AppointmentsSchedulerAppointmentSlotDelegate(onSlotClick:(slotId:Int)->Unit) :
    AppointmentsSchedulerSlotBaseDelegate<AppointmentsSchedulerAppointmentSlotUIModel>(onSlotClick) {


    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is AppointmentsSchedulerAppointmentSlotUIModel

    private lateinit var appointmentView: AppointmentView

    override fun initViewHolder(binding: ItemAppointmentsSchedulerSlotBinding) {
        appointmentView = AppointmentView(binding.root.context)
        binding.slotContainer.addView(appointmentView)
    }

    override fun bindViewHolder(
        binding: ItemAppointmentsSchedulerSlotBinding,
        item: AppointmentsSchedulerAppointmentSlotUIModel
    ) {
       appointmentView.setAppointmentData(item.appointmentUi)
    }
}