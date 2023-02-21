package com.aya.digital.feature.auth.signup.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class SignUpNavigationEvents : CoordinatorEvent() {
    object SignUp : SignUpNavigationEvents()
}