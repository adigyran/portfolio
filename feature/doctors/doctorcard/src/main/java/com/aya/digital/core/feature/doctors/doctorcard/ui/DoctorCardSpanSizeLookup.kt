package com.aya.digital.core.feature.doctors.doctorcard.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx

internal class DoctorCardSpanSizeLookup(private val recyclerView: RecyclerView) : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        val findViewHolderForAdapterPosition =
            recyclerView.findViewHolderForAdapterPosition(position)
        return findViewHolderForAdapterPosition?.let { viewHolder ->
            when(viewHolder)
            {
                else -> -1
            }
        }?: -1
    }


}