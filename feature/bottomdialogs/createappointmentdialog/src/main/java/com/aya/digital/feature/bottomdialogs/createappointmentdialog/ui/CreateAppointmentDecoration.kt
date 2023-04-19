package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorSlotDelegate

internal class CreateAppointmentDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager as GridLayoutManager
        val position = parent.getChildAdapterPosition(view)
        if(position == RecyclerView.NO_POSITION) return
        val viewHolder = parent.findContainingViewHolder(view)
        val column: Int = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        if (viewHolder is DoctorSlotDelegate.ViewHolder) {

            val spacing = when (column) {
                0 -> HorizontalSpacings(20, 2)
                3 -> HorizontalSpacings(2, 20)
                else -> HorizontalSpacings(2, 2)
            }
            outRect.right = spacing.right.dpToPx()
            outRect.left = spacing.left.dpToPx()
        } else {
            outRect.left = (20).dpToPx()
            outRect.right = (20).dpToPx()
        }
        val top = when (viewHolder) {
            is DoctorSlotDelegate.ViewHolder -> 10
            else -> 0
        }
        outRect.top = top.dpToPx()

    }

    private data class HorizontalSpacings(val left: Int, val right: Int)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}