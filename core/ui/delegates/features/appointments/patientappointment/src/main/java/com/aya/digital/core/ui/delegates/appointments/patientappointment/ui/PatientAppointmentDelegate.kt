package com.aya.digital.core.ui.delegates.appointments.patientappointment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.visible
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentBinding

class PatientAppointmentDelegate(private val onAppointmentClick: (id: Int) -> Unit) :
    BaseDelegate<PatientAppointmentUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is PatientAppointmentUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<PatientAppointmentUIModel> {
        val binding =
            ItemPatientAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemPatientAppointmentBinding) :
        BaseViewHolder<PatientAppointmentUIModel>(binding.root) {

        init {
            binding.root bindClick { onAppointmentClick(item.id) }
        }
        override fun bind(item: PatientAppointmentUIModel) {
            super.bind(item)
            binding.tvStartDate.text = item.startDate
            binding.tvDuration.text = item.duration
            if(item.isTelemed) binding.grTelemed.visible() else binding.grTelemed.gone()
            binding.tvAppintmentId.text = "appointment id: %d".format(item.id)
        }
    }
}