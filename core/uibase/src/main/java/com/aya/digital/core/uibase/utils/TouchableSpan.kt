package com.aya.digital.core.uibase.utils

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

class TouchableSpan(
    private val mNormalTextColor: Int,
    private val mPressedTextColor: Int,
    private val onClick: () -> Unit,
) : ClickableSpan() {
    private var mIsPressed: Boolean = false
    override fun onClick(widget: View) {
        onClick()
        widget.invalidate()
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = if (mIsPressed) mPressedTextColor else mNormalTextColor
        ds.isUnderlineText = false
    }

    fun setPressed(isSelected: Boolean) {
        mIsPressed = isSelected
    }
}
