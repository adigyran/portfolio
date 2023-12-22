package com.aya.digital.core.feature.prescriptions.list.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfilePrescriptionsListNavigationEvents : CoordinatorEvent() {
    data class ViewPrescription(val prescriptionId: Int) : ProfilePrescriptionsListNavigationEvents()
    data class EditPrescription(val requestCode: String, val prescriptionId: Int) : ProfilePrescriptionsListNavigationEvents()
}