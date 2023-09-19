package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.AppointmentsSchedulerMultiAppointmentSlotUIModel
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.base.AppointmentsSchedulerSlotBaseDelegate
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.views.MultiAppointmentView
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerSlotBinding

class AppointmentsSchedulerMultiAppointmentSlotDelegate(onSlotClick:(slotId:Int)->Unit) :
    AppointmentsSchedulerSlotBaseDelegate<AppointmentsSchedulerMultiAppointmentSlotUIModel>(onSlotClick) {


    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is AppointmentsSchedulerMultiAppointmentSlotUIModel

    private lateinit var appointmentView: MultiAppointmentView

    override fun initViewHolder(binding: ItemAppointmentsSchedulerSlotBinding) {
        appointmentView = MultiAppointmentView(binding.root.context)
        binding.slotContainer.addView(appointmentView)
    }

    override fun bindViewHolder(
        binding: ItemAppointmentsSchedulerSlotBinding,
        item: AppointmentsSchedulerMultiAppointmentSlotUIModel
    ) {
       appointmentView.setAppointmentData(item.appointmentCountText)
    }
}