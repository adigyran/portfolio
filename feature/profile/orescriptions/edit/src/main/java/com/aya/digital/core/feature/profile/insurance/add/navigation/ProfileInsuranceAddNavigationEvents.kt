package com.aya.digital.core.feature.profile.insurance.add.navigation

import com.aya.digital.core.data.base.result.models.insurance.AddInsuranceResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileInsuranceAddNavigationEvents : CoordinatorEvent() {
    data class SelectInsuranceCompany(val requestCode: String, val selectedInsurance:Int?) :  ProfileInsuranceAddNavigationEvents()
    data class FinishWithResult(val requestCode: String, val result: AddInsuranceResultModel) :
        ProfileInsuranceAddNavigationEvents()

}