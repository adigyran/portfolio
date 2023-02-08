package com.aya.digital.core.navigation.screen

import android.content.Context
import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

abstract class HealthAppActivityScreen(key: String? = null, intentCreator: Creator<Context, Intent>) :
    ActivityScreen(key, intentCreator)