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
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import timber.log.Timber

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
        BaseViewHolder<PatientAppointmentUIModel>(binding.root),StatusHolder {
        init {

            binding.root bindClick { onAppointmentClick(item.id) }
        }
        override fun bind(item: PatientAppointmentUIModel) {
            super.bind(item)
            Timber.d("$item")
            binding.tvAppointmentDate.text = item.startDateTime.first
            binding.tvAppointmentTime.text = item.startDateTime.second
            binding.tvDoctorName.text = item.doctorName
            binding.tvDoctorSpeciality.text = item.doctorSpeciality
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