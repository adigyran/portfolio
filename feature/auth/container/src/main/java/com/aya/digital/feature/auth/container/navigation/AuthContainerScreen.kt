package com.aya.digital.feature.auth.container.navigation

import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.auth.container.ui.AuthContainerView
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AuthContainerScreen : HealthAppFragmentScreen(fragmentCreator = {AuthContainerView.getNewInstance()})
