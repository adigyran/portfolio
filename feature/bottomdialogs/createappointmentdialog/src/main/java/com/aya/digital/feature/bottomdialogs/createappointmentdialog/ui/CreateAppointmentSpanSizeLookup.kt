package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal class CreateAppointmentSpanSizeLookup(private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) :
    GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int = when (adapter?.getItemViewType(position)) {
        0 -> 1
        else -> 4
    }
}