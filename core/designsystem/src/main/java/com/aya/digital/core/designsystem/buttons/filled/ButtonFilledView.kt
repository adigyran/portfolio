package com.aya.digital.core.designsystem.buttons.filled

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import com.aya.digital.core.designsystem.databinding.ButtonFilledBinding

class ButtonFilledView(context: Context, attrs: AttributeSet?) :
    AppCompatButton(context, attrs) {

    private val binding = ButtonFilledBinding.inflate(
        LayoutInflater.from(context)
    )
}