package com.aya.digital.core.feature.profile.emergencycontact.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileEmergencyContactNavigationEvents : CoordinatorEvent()
{
    data class SelectContactType(val requestCode: String, val selectedContactType:Int?) :  ProfileEmergencyContactNavigationEvents()
}