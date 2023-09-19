package com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.schedulerslot.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.aya.digital.core.ui.delegates.features.appointments.appointmentsscheduler.databinding.ViewSlotEmptyBinding

internal class EmptySlotDataView : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private val binding: ViewSlotEmptyBinding =
        ViewSlotEmptyBinding.inflate(LayoutInflater.from(context), this, true)

}