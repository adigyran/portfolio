package com.aya.digital.core.feature.tabviews.profile.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileNavigationEvents : CoordinatorEvent() {
    data class OpenProfileGeneralInfo(val requestCode:String) : ProfileNavigationEvents()
    data object OpenProfileEmergencyContact : ProfileNavigationEvents()
    data object OpenProfileSecurity : ProfileNavigationEvents()
    data object OpenProfileInsurance : ProfileNavigationEvents()
    data object OpenProfileNotification : ProfileNavigationEvents()
    data object OpenProfilePrescriptions : ProfileNavigationEvents()
    data object OpenClinicInfo : ProfileNavigationEvents()
    data object OpenProfileAddress : ProfileNavigationEvents()

}