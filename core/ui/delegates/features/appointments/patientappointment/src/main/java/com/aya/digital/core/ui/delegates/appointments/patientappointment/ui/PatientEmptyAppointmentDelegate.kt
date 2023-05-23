package com.aya.digital.core.ui.delegates.appointments.patientappointment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.core.ext.visible
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientEmptyAppointmentUIModel
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentBinding
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientEmptyAppointmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import timber.log.Timber

class PatientEmptyAppointmentDelegate() :
    BaseDelegate<PatientEmptyAppointmentUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is PatientEmptyAppointmentUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<PatientEmptyAppointmentUIModel> {
        val binding =
            ItemPatientEmptyAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemPatientEmptyAppointmentBinding) :
        BaseViewHolder<PatientEmptyAppointmentUIModel>(binding.root) {

        init {
        }
        override fun bind(item: PatientEmptyAppointmentUIModel) {
            super.bind(item)
            binding.tvDate.text = item.date
        }
    }
}