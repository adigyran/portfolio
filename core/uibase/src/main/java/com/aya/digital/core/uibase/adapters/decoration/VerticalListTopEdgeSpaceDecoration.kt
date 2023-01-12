package com.aya.digital.core.uibase.adapters.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalListTopEdgeSpaceDecoration(
    private val verticalSpaceHeight: Int,
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view) // item position

        if (position == 0) { // top edge
            outRect.top = verticalSpaceHeight
        }
    }
}