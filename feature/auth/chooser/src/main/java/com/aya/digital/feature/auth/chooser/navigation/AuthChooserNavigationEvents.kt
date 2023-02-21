package com.aya.digital.feature.auth.chooser.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class AuthChooserNavigationEvents : CoordinatorEvent() {
    object SignIn : AuthChooserNavigationEvents()
    object SignUp : AuthChooserNavigationEvents()
}