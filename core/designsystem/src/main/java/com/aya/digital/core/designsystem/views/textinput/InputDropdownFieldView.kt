package com.aya.digital.core.designsystem.views.textinput

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.designsystem.databinding.InputDropdownFieldBinding
import com.google.android.material.textfield.TextInputLayout

class InputDropdownFieldView : LinearLayout {
    var textInputLayout: TextInputLayout
    var editText: AutoCompleteTextView
    var binding: InputDropdownFieldBinding =
        InputDropdownFieldBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.InputDropdownFieldView, 0, 0
        )
        val text = a.getString(R.styleable.InputDropdownFieldView_android_text)
        val hint = a.getString(R.styleable.InputDropdownFieldView_android_hint)
        val helperText = a.getString(R.styleable.InputDropdownFieldView_helperText)

        val label = a.getString(R.styleable.InputDropdownFieldView_label)

        a.recycle()

        orientation = VERTICAL

        textInputLayout = binding.tilField
        editText = binding.actMenu


        editText.setText(text ?: "")

        textInputLayout.hint = label ?: (hint ?: "")
        textInputLayout.helperText = helperText ?: ""
    }

    constructor(context: Context) : this(context, null)

    override fun setEnabled(enabled: Boolean) {
        toggleEditTextAvailability(enabled)
        super.setEnabled(enabled)
    }

    fun setHelperText(text: String?) {
        if (textInputLayout.helperText != text) textInputLayout.helperText = text
    }


    private fun toggleEditTextAvailability(enabled: Boolean) {
        editText.isEnabled = enabled
        editText.isClickable = enabled
        editText.isFocusable = enabled
        editText.isFocusableInTouchMode = enabled

    }

    private fun processInputType(inputType: Int) {
        if (inputType != EditorInfo.TYPE_NULL) editText.inputType = inputType
    }
}