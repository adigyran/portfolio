package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.drawables
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ItemAppointmentsSchedulerSlotBinding

abstract class AppointmentsSchedulerSlotBaseDelegate<T : AppointmentsSchedulerBaseSlotUIModel>(
    protected val onSlotClick:(slotId:Int)->Unit) :
BaseDelegate<T>() {


    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<T> {
        val binding =
            ItemAppointmentsSchedulerSlotBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemAppointmentsSchedulerSlotBinding) :
        BaseViewHolder<T>(binding.root) {

        init {
            binding.root bindClick {onSlotClick(item.id)}
            initViewHolder(binding)
        }

        override fun bind(item: T) {
            super.bind(item)
            binding.time.setTimeData(item)
            bindViewHolder(binding, item)

        }


        private fun Int.getBackgroundDrawable() = binding.root.context.drawables[this]
    }

    abstract fun initViewHolder(binding: ItemAppointmentsSchedulerSlotBinding)
    abstract fun bindViewHolder(binding: ItemAppointmentsSchedulerSlotBinding, item: T)
}