package com.aya.digital.core.feature.profile.security.securitysummary.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileSecuritySummaryNavigationEvents : CoordinatorEvent() {
    data class ChangeEmail(val requestCode:String) : ProfileSecuritySummaryNavigationEvents()

    data class ChangePhone(val requestCode:String) : ProfileSecuritySummaryNavigationEvents()

    data class ChangePassword(val requestCode: String) : ProfileSecuritySummaryNavigationEvents()
}