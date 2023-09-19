package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.AppointmentsSchedulerEmptySlotUIModel
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.base.AppointmentsSchedulerSlotBaseDelegate
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.views.EmptySlotDataView
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerSlotBinding

class AppointmentsSchedulerEmptySlotDelegate(onSlotClick:(slotId:Int)->Unit) :
    AppointmentsSchedulerSlotBaseDelegate<AppointmentsSchedulerEmptySlotUIModel>(onSlotClick) {


    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is AppointmentsSchedulerEmptySlotUIModel


    override fun initViewHolder(binding: ItemAppointmentsSchedulerSlotBinding) {
        val emptyView = EmptySlotDataView(binding.root.context)
        binding.slotContainer.addView(emptyView)
    }

    override fun bindViewHolder(
        binding: ItemAppointmentsSchedulerSlotBinding,
        item: AppointmentsSchedulerEmptySlotUIModel
    ) {

    }




}