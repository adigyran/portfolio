package com.aya.digital.core.feature.profile.security.securitysummary.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileSecuritySummaryNavigationEvents : CoordinatorEvent() {
    object ChangeEmail : ProfileSecuritySummaryNavigationEvents()
    object ChangePassword : ProfileSecuritySummaryNavigationEvents()
}