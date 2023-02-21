package com.aya.digital.core.feature.auth.signin.navigation

import com.aya.digital.core.feature.auth.signin.ui.SignInView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object SignInScreen : HealthAppFragmentScreen(fragmentCreator = { SignInView() })
