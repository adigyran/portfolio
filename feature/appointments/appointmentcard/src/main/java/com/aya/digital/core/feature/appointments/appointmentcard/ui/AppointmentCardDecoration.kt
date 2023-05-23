package com.aya.digital.core.feature.appointments.appointmentcard.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.AppointmentTelemedDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsBioDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsInsuranceDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsLocationDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsTitleDelegate


internal class AppointmentCardDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val top = when (parent.findContainingViewHolder(view)) {
            is AppointmentTelemedDelegate.ViewHolder -> 16
            is DoctorDetailsBioDelegate.ViewHolder -> 8
            is DoctorDetailsTitleDelegate.ViewHolder -> 20
            is DoctorDetailsLocationDelegate.ViewHolder -> 8
            is DoctorDetailsInsuranceDelegate.ViewHolder -> 12
            else -> 0
        }
        val horizontal = 20
        outRect.top = top.dpToPx()
        outRect.left = horizontal.dpToPx()
        outRect.right = horizontal.dpToPx()
    }

    private data class HorizontalSpacings(val left: Int, val right: Int)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}