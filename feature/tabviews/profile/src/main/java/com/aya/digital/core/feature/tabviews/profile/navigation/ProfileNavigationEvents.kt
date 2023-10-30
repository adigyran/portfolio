package com.aya.digital.core.feature.tabviews.profile.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileNavigationEvents : CoordinatorEvent() {
    data class OpenProfileGeneralInfo(val requestCode:String) : ProfileNavigationEvents()
    object OpenProfileEmergencyContact : ProfileNavigationEvents()
    object OpenProfileSecurity : ProfileNavigationEvents()
    object OpenProfileInsurance : ProfileNavigationEvents()
    object OpenProfileNotification : ProfileNavigationEvents()
    object OpenClinicInfo : ProfileNavigationEvents()
    object OpenProfileAddress : ProfileNavigationEvents()

}