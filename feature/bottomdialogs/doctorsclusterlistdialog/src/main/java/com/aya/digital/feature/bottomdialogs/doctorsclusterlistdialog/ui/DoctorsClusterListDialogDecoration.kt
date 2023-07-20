package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx

internal class DoctorsClusterListDialogDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (20).dpToPx()
        val top = (8).dpToPx()
        outRect.top = top
        outRect.left = horizontal
        outRect.right = horizontal

    }

    private data class HorizontalSpacings(val left: Int, val right: Int)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}