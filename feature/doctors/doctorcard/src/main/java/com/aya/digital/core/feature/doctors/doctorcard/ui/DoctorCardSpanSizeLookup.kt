package com.aya.digital.core.feature.doctors.doctorcard.ui

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal class DoctorCardSpanSizeLookup(private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) :
    GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int = when (adapter?.getItemViewType(position)) {
        2 -> 1
        else -> 4
    }
}