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
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.DoctorAppointmentUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemDoctorAppointmentBinding
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import timber.log.Timber

class DoctorAppointmentDelegate(private val onAppointmentClick: (id: Int) -> Unit) :
    BaseDelegate<DoctorAppointmentUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorAppointmentUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorAppointmentUIModel> {
        val binding =
            ItemDoctorAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorAppointmentBinding) :
        BaseViewHolder<DoctorAppointmentUIModel>(binding.root),StatusHolder {
        init {

            binding.root bindClick { onAppointmentClick(item.id) }
        }
        override fun bind(item: DoctorAppointmentUIModel) {
            super.bind(item)
            Timber.d("$item")
            binding.tvAppointmentDate.text = item.startDateTime.first
            binding.tvAppointmentTime.text = item.startDateTime.second
            binding.tvName.text = item.name
            binding.tvAge.text = item.age
            binding.clTelemed.toggleVisibility(item.isTelemed, View.GONE)
            item.participantAvatarLink?.let {
                Glide
                    .with(binding.ivAvatar)
                    .load(it)
                    .transform(
                        CircleCrop()
                    )
                    .dontAnimate()
                    .into(binding.ivAvatar)
            }
        }

        override fun getDelegateStatus(): AppointmentUiStatus = item.status
    }
}