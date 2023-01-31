package com.aya.digital.core.designsystem.buttons.outlined

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import com.aya.digital.core.designsystem.databinding.ButtonOutlinedBinding

class ButtonOutlinedView(context: Context, attrs: AttributeSet?) :
    AppCompatButton(context, attrs) {

    private val binding = ButtonOutlinedBinding.inflate(
        LayoutInflater.from(context)
    )
}