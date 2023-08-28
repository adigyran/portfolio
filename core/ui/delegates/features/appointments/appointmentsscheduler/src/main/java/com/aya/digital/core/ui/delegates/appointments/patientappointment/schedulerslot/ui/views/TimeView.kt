package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.aya.digital.core.designsystem.databinding.SearchViewFieldBinding
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.base.AppointmentsSchedulerBaseSlotUIModel
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotAppointmentBinding
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotTimeBinding
import com.google.android.material.search.SearchView

internal class TimeView : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private val binding: ViewSlotTimeBinding =
        ViewSlotTimeBinding.inflate(LayoutInflater.from(context), this, true)


    fun setTimeData(baseAppointmentSlot: AppointmentsSchedulerBaseSlotUIModel) {
        binding.timeTv.text = baseAppointmentSlot.timeText
    }
}