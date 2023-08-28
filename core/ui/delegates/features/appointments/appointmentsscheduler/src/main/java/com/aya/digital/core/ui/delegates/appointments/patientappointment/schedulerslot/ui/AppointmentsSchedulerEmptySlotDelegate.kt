package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerday.model.AppointmentsSchedulerDayUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentsSchedulerEmptySlotUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.ui.base.AppointmentsSchedulerSlotBaseDelegate
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.ui.views.EmptySlotDataView
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerDayBinding
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerSlotBinding
import java.time.LocalDate

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