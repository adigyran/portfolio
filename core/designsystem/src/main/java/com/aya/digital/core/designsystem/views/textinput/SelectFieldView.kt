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
import com.aya.digital.core.designsystem.databinding.InputSelectFieldBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SelectFieldView : LinearLayout {
    var textInputLayout: TextInputLayout
    var editText: TextInputEditText

    var binding: InputSelectFieldBinding =
        InputSelectFieldBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.SelectFieldView, 0, 0
        )
        val label = a.getString(R.styleable.InputFieldView_label)
        val text = a.getString(R.styleable.SelectFieldView_android_text)
        val hint = a.getString(R.styleable.SelectFieldView_android_hint)
        val helperText = a.getString(R.styleable.SelectFieldView_helperText)

        val isArrowVisible = a.getBoolean(R.styleable.SelectFieldView_isArrowVisible, false)

        a.recycle()

        orientation = VERTICAL
        isClickable = true

        textInputLayout = this.findViewById(R.id.tilField)
        editText = this.findViewById(R.id.edField)

        editText.setText(text ?: "")
        // editText.hint = hint ?: ""
        editText.movementMethod = null
        editText.keyListener = null

        textInputLayout.hint = label ?: (hint ?: "")
        textInputLayout.helperText = helperText ?: ""


        textInputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        textInputLayout.isEndIconVisible = !isArrowVisible
    }

    fun setText(text: String) = editText.setText(text)

    fun getText() = editText.text

    fun setHintText(text: String?)
    {
        if (textInputLayout.hint != text) textInputLayout.hint = text
    }

    constructor(context: Context) : this(context, null)
}