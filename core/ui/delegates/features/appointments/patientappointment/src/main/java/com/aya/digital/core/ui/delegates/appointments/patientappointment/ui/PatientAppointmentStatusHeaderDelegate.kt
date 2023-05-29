package com.aya.digital.core.ui.delegates.appointments.patientappointment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentsStatusHeaderUIModel
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentStatusHeaderBinding

class PatientAppointmentStatusHeaderDelegate() :
    BaseDelegate<PatientAppointmentsStatusHeaderUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is PatientAppointmentsStatusHeaderUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<PatientAppointmentsStatusHeaderUIModel> {
        val binding =
            ItemPatientAppointmentStatusHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemPatientAppointmentStatusHeaderBinding) :
        BaseViewHolder<PatientAppointmentsStatusHeaderUIModel>(binding.root),StatusHolder {
        lateinit var status: AppointmentUiStatus
        init {
        }
        override fun bind(item: PatientAppointmentsStatusHeaderUIModel) {
            super.bind(item)
            status = item.status
            binding.headerTitle.setText(item.status.nameId)
        }

        override fun getDelegateStatus(): AppointmentUiStatus = item.status
    }
}