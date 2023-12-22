package com.aya.digital.core.feature.prescriptions.list.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfilePrescriptionsListNavigationEvents : CoordinatorEvent() {
    data class AddPrescriptions(val requestCode:String) : ProfilePrescriptionsListNavigationEvents()
    data class EditPrescriptions(val requestCode: String, val insuranceId: Int) : ProfilePrescriptionsListNavigationEvents()
}