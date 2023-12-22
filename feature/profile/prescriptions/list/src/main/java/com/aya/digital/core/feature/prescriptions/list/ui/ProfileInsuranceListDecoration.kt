package com.aya.digital.core.feature.prescriptions.list.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.profile.insurance.ui.InsurancePolicyDelegate

internal class ProfileInsuranceListDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (12).dpToPx()
        val top = when(viewHolder)
        {
            is InsurancePolicyDelegate.ViewHolder -> (8).dpToPx()
            else -> 0
        }
        outRect.top = top
        outRect.left = horizontal
        outRect.right = horizontal
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}