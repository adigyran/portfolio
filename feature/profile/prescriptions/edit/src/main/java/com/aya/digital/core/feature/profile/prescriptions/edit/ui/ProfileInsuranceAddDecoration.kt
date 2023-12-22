package com.aya.digital.core.feature.profile.prescriptions.edit.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegate
import com.aya.digital.core.ui.delegates.profile.insurance.ui.InsurancePolicyPhotoDelegate

internal class ProfileInsuranceAddDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (20).dpToPx()
        val top = when(viewHolder)
        {
            is InsurancePolicyPhotoDelegate.ViewHolder -> (24).dpToPx()
            is NameFieldDelegate.ViewHolder -> (12).dpToPx()
            is SelectionFieldDelegate.ViewHolder -> (32).dpToPx()
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