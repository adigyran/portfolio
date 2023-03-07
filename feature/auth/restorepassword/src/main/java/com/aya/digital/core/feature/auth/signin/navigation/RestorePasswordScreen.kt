package com.aya.digital.core.feature.auth.signin.navigation

import com.aya.digital.core.feature.auth.signin.ui.RestorePasswordView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object RestorePasswordScreen : HealthAppFragmentScreen(fragmentCreator = { RestorePasswordView() })
