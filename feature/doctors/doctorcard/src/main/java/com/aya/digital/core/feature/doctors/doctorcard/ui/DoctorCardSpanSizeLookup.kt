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

internal class DoctorCardSpanSizeLookup(private val recyclerView: RecyclerView) : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        val findViewHolderForAdapterPosition =
            recyclerView.findViewHolderForAdapterPosition(position)
        Timber.d(findViewHolderForAdapterPosition.toString())
        return findViewHolderForAdapterPosition?.let { viewHolder ->
            when(viewHolder)
            {
                is DoctorDetailsTitleDelegate.ViewHolder -> 4
                is DoctorDetailsBioDelegate.ViewHolder -> 4
                is DoctorDetailsInsuranceDelegate.ViewHolder -> 4
                is DoctorDateTitleDelegate.ViewHolder -> 4
                is DoctorSlotDelegate.ViewHolder -> 1
                else -> -1
            }
        }?: 4
    }


}