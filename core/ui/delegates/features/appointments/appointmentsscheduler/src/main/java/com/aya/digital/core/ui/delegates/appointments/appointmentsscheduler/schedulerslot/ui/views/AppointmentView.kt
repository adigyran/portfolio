package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.model.AppointmentUi
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotAppointmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

internal class AppointmentView: ConstraintLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private val binding: ViewSlotAppointmentBinding =
        ViewSlotAppointmentBinding.inflate(LayoutInflater.from(context), this, true)


    fun setAppointmentData(appointmentUi: AppointmentUi) {
        binding.tPatientName.text = appointmentUi.patient?.name?:""
        binding.telemedStatus.toggleVisibility(appointmentUi.isTelemedicine)
        appointmentUi.patient?.patientAvatar?.let {
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
}