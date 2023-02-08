package com.aya.digital.feature.auth.chooser.navigation

import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.auth.chooser.ui.AuthChooserView
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AuthChooserScreen : HealthAppFragmentScreen(fragmentCreator = {AuthChooserView()})
