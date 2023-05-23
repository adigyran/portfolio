package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.AppointmentTelemedUIModel
import com.aya.digital.core.ui.delegates.features.appointmentcard.appointmentdetails.databinding.ItemAppointmentTelemedBinding

class AppointmentTelemedDelegate(val onTelemed:() -> Unit) :
    BaseDelegate<AppointmentTelemedUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is AppointmentTelemedUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<AppointmentTelemedUIModel> {
        val binding =
            ItemAppointmentTelemedBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemAppointmentTelemedBinding) :
        BaseViewHolder<AppointmentTelemedUIModel>(binding.root) {

        init {
            binding.root bindClick {onTelemed()}
        }
        override fun bind(item: AppointmentTelemedUIModel) {
            super.bind(item)
        }
    }
}