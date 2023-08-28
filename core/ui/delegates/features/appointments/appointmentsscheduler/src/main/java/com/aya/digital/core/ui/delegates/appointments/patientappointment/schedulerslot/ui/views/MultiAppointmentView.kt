package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.aya.digital.core.designsystem.databinding.SearchViewFieldBinding
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentUi
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotAppointmentBinding
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotEmptyBinding
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotMultiappointmentBinding
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotTimeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.search.SearchView

internal class MultiAppointmentView: LinearLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private val binding: ViewSlotMultiappointmentBinding =
        ViewSlotMultiappointmentBinding.inflate(LayoutInflater.from(context), this, true)


    fun setAppointmentData(appointmentsCountText:String) {
        binding.tvAppointmentsCount.text = appointmentsCountText
    }
}