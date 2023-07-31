package com.aya.digital.feature.auth.signup.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailPhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegate

internal class SignUpDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val horizontal = (20).dpToPx()
        val bottom = when (viewHolder) {
            is HeadlineLabelDelegate.ViewHolder -> (32).dpToPx()
            is EmailPhoneFieldDelegate.ViewHolder -> (12).dpToPx()
            is PasswordFieldDelegate.ViewHolder -> (12).dpToPx()
            is SelectionFieldDelegate.ViewHolder -> (12).dpToPx()
            is NameFieldDelegate.ViewHolder -> (12).dpToPx()
            else -> 0
        }
        outRect.bottom = bottom
        outRect.left = horizontal
        outRect.right = horizontal
    }
}