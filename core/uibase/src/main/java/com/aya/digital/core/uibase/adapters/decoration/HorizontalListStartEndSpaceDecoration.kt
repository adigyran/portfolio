package com.aya.digital.core.uibase.adapters.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalListStartEndSpaceDecoration(
    private val verticalSpaceHeight: Int, private val includeStartEdge: Boolean = true,
    private val includeEndEdge: Boolean = true,
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view) // item position

        val isFirstItem = position == 0
        val isLastItem = position == parent.adapter!!.itemCount - 1

        var leftMargin = verticalSpaceHeight / 2
        var rightMargin = verticalSpaceHeight / 2

        if (isFirstItem && !includeStartEdge) leftMargin = 0
        if (isLastItem && !includeEndEdge) rightMargin = 0

        outRect.left = leftMargin
        outRect.right = rightMargin
    }
}