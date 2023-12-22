package com.aya.digital.core.feature.profile.prescriptions.view.navigation

import com.aya.digital.core.data.base.result.models.insurance.AddInsuranceResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfilePrescriptionsViewNavigationEvents : CoordinatorEvent() {
    data class SelectInsuranceCompany(val requestCode: String, val selectedInsurance:Int?) :  ProfilePrescriptionsViewNavigationEvents()
    data class FinishWithResult(val requestCode: String, val result: AddInsuranceResultModel) :
        ProfilePrescriptionsViewNavigationEvents()

}