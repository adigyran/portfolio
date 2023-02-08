package com.aya.digital.core.ui.adapters.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalListTopEdgeSpaceGridDecoration(
    private val verticalSpaceHeight: Int,
    private val gridSize: Int = 0,
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view) // item position

        if (position < gridSize) { // top edge
            outRect.top = verticalSpaceHeight
        }
    }
}