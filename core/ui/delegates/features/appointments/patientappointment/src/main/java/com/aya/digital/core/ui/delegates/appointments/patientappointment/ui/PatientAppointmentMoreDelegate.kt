package com.aya.digital.core.ui.delegates.appointments.patientappointment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentMoreUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentsStatusFooterUIModel
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentMoreBinding
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentStatusFooterBinding

class PatientAppointmentMoreDelegate() :
    BaseDelegate<PatientAppointmentMoreUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is PatientAppointmentMoreUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<PatientAppointmentMoreUIModel> {
        val binding =
            ItemPatientAppointmentMoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemPatientAppointmentMoreBinding) :
        BaseViewHolder<PatientAppointmentMoreUIModel>(binding.root), StatusHolder {
        lateinit var status: AppointmentUiStatus

        init {

        }

        override fun bind(item: PatientAppointmentMoreUIModel) {
            super.bind(item)
            status = item.status
        }

        override fun getDelegateStatus(): AppointmentUiStatus = item.status
    }
}