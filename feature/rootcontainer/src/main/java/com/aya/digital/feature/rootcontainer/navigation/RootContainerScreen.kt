package com.aya.digital.feature.rootcontainer.navigation

import android.content.Intent
import com.aya.digital.core.navigation.screen.HealthAppActivityScreen
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.rootcontainer.ui.RootView

object RootContainerScreen :
    HealthAppActivityScreen(intentCreator = { RootView.getNewInstance(it) })
