package com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx


internal class AppointmentsSchedulerDaysTabDecoration(private val context: Context) :
    RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (6).dpToPx()
        outRect.left = horizontal
        outRect.right = horizontal
    }



}