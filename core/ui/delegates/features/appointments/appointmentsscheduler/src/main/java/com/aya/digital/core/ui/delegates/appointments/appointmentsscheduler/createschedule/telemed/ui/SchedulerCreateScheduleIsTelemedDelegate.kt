package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.createschedule.telemed.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.disable
import com.aya.digital.core.ext.drawables
import com.aya.digital.core.ext.toggleAvailability
import com.aya.digital.core.ext.toggleSelection
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.createschedule.telemed.model.SchedulerCreateScheduleIsTelemedUIModel
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerday.model.AppointmentsSchedulerDayUIModel
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.R
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerCreateScheduleTelemedBinding
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerDayBinding
import java.time.LocalDate

class SchedulerCreateScheduleIsTelemedDelegate(private val onTelemedChecked:(checked:Boolean)->Unit) :
    BaseDelegate<SchedulerCreateScheduleIsTelemedUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is SchedulerCreateScheduleIsTelemedUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<SchedulerCreateScheduleIsTelemedUIModel> {
        val binding =
            ItemAppointmentsSchedulerCreateScheduleTelemedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemAppointmentsSchedulerCreateScheduleTelemedBinding) :
        BaseViewHolder<SchedulerCreateScheduleIsTelemedUIModel>(binding.root) {

        init {
            binding.root bindClick {onTelemedChecked(true)}
        }

        override fun bind(item: SchedulerCreateScheduleIsTelemedUIModel) {
            super.bind(item)

            binding.isTelemedSwitch.isChecked = item.isTelemed
        }


        private fun Int.getBackgroundDrawable() = binding.root.context.drawables[this]
    }
}