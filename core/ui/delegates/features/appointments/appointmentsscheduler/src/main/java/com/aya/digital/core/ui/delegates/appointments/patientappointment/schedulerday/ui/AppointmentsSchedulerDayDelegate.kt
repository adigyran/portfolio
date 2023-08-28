package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerday.ui

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
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerday.model.AppointmentsSchedulerDayUIModel
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.R
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerDayBinding
import timber.log.Timber
import java.time.LocalDate

class AppointmentsSchedulerDayDelegate(private val onClick:(LocalDate)->Unit, private val onDayPosition:(LocalDate,Int)->Unit) :
    BaseDelegate<AppointmentsSchedulerDayUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is AppointmentsSchedulerDayUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<AppointmentsSchedulerDayUIModel> {
        val binding =
            ItemAppointmentsSchedulerDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemAppointmentsSchedulerDayBinding) :
        BaseViewHolder<AppointmentsSchedulerDayUIModel>(binding.root) {

        init {
            binding.root bindClick {onClick(item.day)}

        }

        override fun bind(item: AppointmentsSchedulerDayUIModel) {
            super.bind(item)
            binding.dayOfWeekNameTv.text = item.dayOfWeekName
            binding.dayOfMonthTv.text = item.dayOfMonthText
            if (!item.hasSlots) {
                binding.ivIndicator.disable()
                binding.root.background =
                    R.drawable.bg_appointments_scheduler_empty_day.getBackgroundDrawable()
                return
            }
            binding.root.toggleSelection(item.selected)
            binding.ivIndicator.toggleAvailability(item.hasAppointments)

        }


        private fun Int.getBackgroundDrawable() = binding.root.context.drawables[this]
    }
}