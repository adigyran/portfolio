package com.aya.digital.core.feature.profile.security.changepassword.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineTwoLineLabelDelegate

internal class ProfileSecurityChangePasswordDecoration : RecyclerView.ItemDecoration() {

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
            is HeadlineTwoLineLabelDelegate.ViewHolder -> (24).dpToPx()
            else -> 0
        }
        val bottom = when(viewHolder)
        {
            is HeadlineTwoLineLabelDelegate.ViewHolder -> (24).dpToPx()
            is PasswordFieldDelegate.ViewHolder -> (20).dpToPx()
            else -> 0
        }
        outRect.top = top
        outRect.bottom = bottom
        outRect.left = horizontal
        outRect.right = horizontal
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }
}