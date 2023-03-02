package com.aya.digital.core.feature.auth.signin.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class SignInNavigationEvents : CoordinatorEvent() {
    object SignUp : SignInNavigationEvents()

    object SignedIn : SignInNavigationEvents()
}