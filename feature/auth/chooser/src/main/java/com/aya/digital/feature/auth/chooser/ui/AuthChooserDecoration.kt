package com.aya.digital.feature.auth.chooser.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui.ChooserButtonsDelegate
import com.aya.digital.core.ui.delegates.auth.chooser.description.ui.ChooserDescriptionDelegate

internal class AuthChooserDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (20).dpToPx()
        val bottom = when(viewHolder)
        {
           is ChooserButtonsDelegate.ViewHolder -> (16).dpToPx()
           else -> 0
        }
        outRect.bottom = bottom
        outRect.left = horizontal
        outRect.right = horizontal
    }
}