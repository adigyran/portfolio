package com.aya.digital.core.designsystem.buttons.text

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import com.aya.digital.core.designsystem.databinding.ButtonTextBinding

class ButtonTextView(context: Context, attrs: AttributeSet?) :
    AppCompatButton(context, attrs) {

    private val binding = ButtonTextBinding.inflate(
        LayoutInflater.from(context)
    )
}