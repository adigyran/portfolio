package com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx


internal class AppointmentsSchedulerSlotsTabDecoration(private val context: Context) :
    RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (10).dpToPx()
        val top = when(viewHolder)
        {
           // is PatientAppointmentMoreDelegate.ViewHolder -> 0
            else -> (8).dpToPx()
        }
        outRect.top = top
        outRect.left = horizontal
        outRect.right = horizontal
    }



}