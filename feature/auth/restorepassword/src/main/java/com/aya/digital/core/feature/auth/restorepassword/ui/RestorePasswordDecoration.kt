package com.aya.digital.core.feature.auth.restorepassword.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegate

internal class RestorePasswordDecoration : RecyclerView.ItemDecoration() {

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
           is HeadlineLabelDelegate.ViewHolder -> (32).dpToPx()
           is PasswordFieldDelegate.ViewHolder -> (12).dpToPx()
           else -> 0
        }
        outRect.bottom = bottom
        outRect.left = horizontal
        outRect.right = horizontal
    }
}