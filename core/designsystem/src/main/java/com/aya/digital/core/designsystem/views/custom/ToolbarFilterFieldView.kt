package com.aya.digital.core.designsystem.views.custom

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.designsystem.databinding.InputFieldBinding
import com.aya.digital.core.designsystem.databinding.ToolbarHomeFilterFieldBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ToolbarFilterFieldView : LinearLayout {
    var binding: ToolbarHomeFilterFieldBinding =
        ToolbarHomeFilterFieldBinding.inflate(LayoutInflater.from(context),this,true)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ToolbarFilterFieldView, 0, 0
        )
        val text = a.getString(R.styleable.ToolbarFilterFieldView_filterLabel)
        val icon = a.getDrawable(R.styleable.ToolbarFilterFieldView_filterIcon)
        binding.fieldText.setText(text)
        binding.fieldIcon.setImageDrawable(icon)
        a.recycle()
    }

    constructor(context: Context) : this(context, null)


}