package com.aya.digital.core.navigation.screen

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

abstract class HealthAppActionScreen(key: String?, intentCreator: Creator<Context, Intent>) :
    ActivityScreen(key, intentCreator)