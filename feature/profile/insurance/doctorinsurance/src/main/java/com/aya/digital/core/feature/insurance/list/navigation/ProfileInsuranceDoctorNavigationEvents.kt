package com.aya.digital.core.feature.insurance.list.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileInsuranceDoctorNavigationEvents : CoordinatorEvent() {
    data class AddInsurance(val requestCode:String) : ProfileInsuranceDoctorNavigationEvents()
    data class EditInsurance(val requestCode: String, val insuranceId: Int) : ProfileInsuranceDoctorNavigationEvents()
}