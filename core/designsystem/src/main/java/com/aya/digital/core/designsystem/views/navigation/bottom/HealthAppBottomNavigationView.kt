package com.aya.digital.core.designsystem.views.navigation.bottom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.aya.digital.core.designsystem.databinding.BottomNavigationBinding
import com.aya.digital.core.designsystem.databinding.InputDropdownFieldBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HealthAppBottomNavigationView(context: Context, attrs: AttributeSet?) :
    BottomNavigationView(context, attrs) {

    var binding: BottomNavigationBinding =
        BottomNavigationBinding.inflate(LayoutInflater.from(context))
}