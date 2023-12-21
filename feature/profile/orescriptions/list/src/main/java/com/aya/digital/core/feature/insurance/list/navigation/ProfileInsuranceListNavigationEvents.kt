package com.aya.digital.core.feature.insurance.list.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileInsuranceListNavigationEvents : CoordinatorEvent() {
    data class AddInsurance(val requestCode:String) : ProfileInsuranceListNavigationEvents()
    data class EditInsurance(val requestCode: String, val insuranceId: Int) : ProfileInsuranceListNavigationEvents()
}