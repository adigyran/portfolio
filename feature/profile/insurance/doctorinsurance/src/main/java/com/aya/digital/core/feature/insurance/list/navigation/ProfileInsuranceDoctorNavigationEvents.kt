package com.aya.digital.core.feature.insurance.list.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileInsuranceDoctorNavigationEvents : CoordinatorEvent() {
    data class SelectInsuranceCompanies(val requestCode: String, val organisationIds: List<Int>?) : ProfileInsuranceDoctorNavigationEvents()
}