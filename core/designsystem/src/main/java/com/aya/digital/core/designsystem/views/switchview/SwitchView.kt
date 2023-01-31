package com.aya.digital.core.designsystem.views.switchview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.aya.digital.core.designsystem.databinding.BottomNavigationBinding
import com.aya.digital.core.designsystem.databinding.SwitchViewBinding
import com.google.android.material.materialswitch.MaterialSwitch

class SwitchView(context: Context, attrs: AttributeSet?) : MaterialSwitch(context, attrs) {
    var binding: SwitchViewBinding =
        SwitchViewBinding.inflate(LayoutInflater.from(context))
}