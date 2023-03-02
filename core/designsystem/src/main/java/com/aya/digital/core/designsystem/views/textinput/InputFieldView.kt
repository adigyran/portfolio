package com.aya.digital.core.designsystem.views.textinput

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.designsystem.databinding.InputFieldBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputFieldView : LinearLayout {
    var textInputLayout: TextInputLayout
    var editText: TextInputEditText

    var inputType: Int
    var binding: InputFieldBinding =
        InputFieldBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.InputFieldView, 0, 0
        )
        val text = a.getString(R.styleable.InputFieldView_android_text)
        val hint = a.getString(R.styleable.InputFieldView_android_hint)
        val helperText = a.getString(R.styleable.InputFieldView_helperText)

        val imeOptions = a.getString(R.styleable.InputFieldView_android_imeOptions)
        inputType = a.getInt(R.styleable.InputFieldView_android_inputType, EditorInfo.TYPE_NULL)
        val maxLength = a.getInt(R.styleable.InputFieldView_android_maxLength, -1)
        val maxLines = a.getInt(R.styleable.InputFieldView_android_maxLines, -1)
        val singleLine = a.getBoolean(R.styleable.InputFieldView_android_singleLine, false)
        val arrowDownVisible = a.getBoolean(R.styleable.InputFieldView_arrowDownVisible, false)

        val label = a.getString(R.styleable.InputFieldView_label)

        a.recycle()

        orientation = VERTICAL

        textInputLayout = binding.tilField
        editText = binding.edField

        editText.maxLines = maxLines
        editText.isSingleLine = singleLine

        processInputType(inputType)

        editText.setText(text ?: "")
        //  editText.hint = hint ?: ""
        if (maxLength >= 0) {
            editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        } else {
            editText.filters = arrayOfNulls(0)
        }

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

    fun setHintText(text: String?)
    {
        if (textInputLayout.hint != text) textInputLayout.hint = text
    }



    private fun toggleEditTextAvailability(enabled: Boolean) {
        editText.isEnabled = enabled
        editText.isClickable = enabled
        editText.isFocusable = enabled
        editText.isFocusableInTouchMode = enabled

        if (enabled) {
            processInputType(inputType)
        } else {
            editText.movementMethod = null
            editText.keyListener = null
        }
    }

    private fun processInputType(inputType: Int) {
        if (inputType != EditorInfo.TYPE_NULL) editText.inputType = inputType
    }
}