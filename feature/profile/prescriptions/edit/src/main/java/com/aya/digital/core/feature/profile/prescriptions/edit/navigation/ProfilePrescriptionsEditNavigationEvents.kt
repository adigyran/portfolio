package com.aya.digital.core.feature.profile.prescriptions.edit.navigation

import com.aya.digital.core.data.base.result.models.insurance.AddInsuranceResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfilePrescriptionsEditNavigationEvents : CoordinatorEvent() {
    data class SelectInsuranceCompany(val requestCode: String, val selectedInsurance:Int?) :  ProfilePrescriptionsEditNavigationEvents()
    data class FinishWithResult(val requestCode: String, val result: AddInsuranceResultModel) :
        ProfilePrescriptionsEditNavigationEvents()

}