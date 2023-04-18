package com.aya.digital.core.feature.doctors.doctorcard.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsBioDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsInsuranceDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorDateTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorSlotDelegate
import timber.log.Timber

internal class DoctorCardDecoration : RecyclerView.ItemDecoration() {

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
            is DoctorDateTitleDelegate.ViewHolder -> (if(position == 0) 10 else 24 )
            is DoctorSlotDelegate.ViewHolder -> 10
            is DoctorDetailsTitleDelegate.ViewHolder -> (if(position == 0) 20 else 40 )
            is DoctorDetailsBioDelegate.ViewHolder -> 8
            is DoctorDetailsInsuranceDelegate.ViewHolder -> 12
            else -> 0
        }
        outRect.top = top.dpToPx()

    }

    private data class HorizontalSpacings(val left: Int, val right: Int)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}