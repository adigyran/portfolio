package com.aya.digital.core.feature.profile.security.changeemailphone.navigation

import com.aya.digital.core.data.base.result.models.insurance.AddInsuranceResultModel
import com.aya.digital.core.data.base.result.models.profile.ProfileEmailChangeResult
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileSecurityChangeEmailPhoneNavigationEvents : CoordinatorEvent() {

    data class EnterCode(val requestCode:String, val email:String) : ProfileSecurityChangeEmailPhoneNavigationEvents()

    data class FinishWithResult(val requestCode: String, val result: ProfileEmailChangeResult) :
        ProfileSecurityChangeEmailPhoneNavigationEvents()

}