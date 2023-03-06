package com.aya.digital.core.feature.insurance.list.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileInsuranceListNavigationEvents : CoordinatorEvent() {
    object AddInsurance : ProfileInsuranceListNavigationEvents()
    data class EditInsurance(val id:Int) : ProfileInsuranceListNavigationEvents()
}