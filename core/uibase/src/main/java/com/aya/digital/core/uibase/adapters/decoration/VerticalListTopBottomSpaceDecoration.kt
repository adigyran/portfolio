package com.aya.digital.core.uibase.adapters.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalListTopBottomSpaceDecoration(
    private val verticalSpaceHeight: Int, private val includeTopEdge: Boolean = true,
    private val includeBottomEdge: Boolean = true,
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view) // item position

        if (position == 0 && includeTopEdge) { // top edge
            outRect.top = verticalSpaceHeight
        }

        if (position == parent.adapter!!.itemCount - 1 && includeBottomEdge) {
            outRect.bottom = verticalSpaceHeight // item bottom
        }

        if (position != parent.adapter!!.itemCount - 1) {
            outRect.bottom = verticalSpaceHeight // item bottom
        }
    }
}