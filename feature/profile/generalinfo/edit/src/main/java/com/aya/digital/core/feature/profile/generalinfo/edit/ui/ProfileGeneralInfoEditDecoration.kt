package com.aya.digital.core.feature.profile.generalinfo.edit.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx

internal class ProfileGeneralInfoEditDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (20).dpToPx()
        val top = if(itemPosition == 0) (20).dpToPx() else (12).dpToPx()
        outRect.top = top
        outRect.left = horizontal
        outRect.right = horizontal
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}