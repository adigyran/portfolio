package com.aya.digital.core.ui.delegates.appointments.patientappointment.ui

import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.strings
import com.aya.digital.core.localisation.R
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentsStatusFooterUIModel
import com.aya.digital.core.ui.delegates.features.appointments.patientappointment.databinding.ItemPatientAppointmentStatusFooterBinding

class PatientAppointmentStatusFooterDelegate(private val onHideClick: (status: AppointmentUiStatus) -> Unit) :
    BaseDelegate<PatientAppointmentsStatusFooterUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is PatientAppointmentsStatusFooterUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<PatientAppointmentsStatusFooterUIModel> {
        val binding =
            ItemPatientAppointmentStatusFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemPatientAppointmentStatusFooterBinding) :
        BaseViewHolder<PatientAppointmentsStatusFooterUIModel>(binding.root), StatusHolder {
        lateinit var status: AppointmentUiStatus


        init {
            binding.btnHide.movementMethod = LinkTouchMovementMethod()
        }


        override fun bind(item: PatientAppointmentsStatusFooterUIModel) {
            super.bind(item)
            status = item.status
            val descriptionText = with(binding.btnHide.context)
            {
                strings[R.string.hide_button].createSpannableText(
                    colors[com.aya.digital.core.designsystem.R.color.button_text_blue],
                    colors[com.aya.digital.core.designsystem.R.color.button_bg_dark_blue],
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
                    binding.btnHide.context,
                    com.aya.digital.core.designsystem.R.style.TextAppearance_App_Body_DescriptionMiniText,
                    com.aya.digital.core.designsystem.R.style.TextAppearance_App_ButtonText_Default,
                    listOf(SpannableObject(if(item.expanded) strings[R.string.hide] else strings[R.string.show]) { onHideClick(item.status) },)
                )
            }
            binding.btnHide.text = descriptionText
        }

        override fun getDelegateStatus(): AppointmentUiStatus = item.status
    }
}