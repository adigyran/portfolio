package com.aya.digital.core.feature.profile.security.changepassword.navigation

import com.aya.digital.core.data.base.result.models.insurance.AddInsuranceResultModel
import com.aya.digital.core.data.base.result.models.profile.ProfilePasswordChangeResult
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileSecurityChangePasswordNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode: String, val result: ProfilePasswordChangeResult) :
        ProfileSecurityChangePasswordNavigationEvents()

}