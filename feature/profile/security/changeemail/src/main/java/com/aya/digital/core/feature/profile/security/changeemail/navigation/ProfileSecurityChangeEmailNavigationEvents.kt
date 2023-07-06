package com.aya.digital.core.feature.profile.security.changeemail.navigation

import com.aya.digital.core.data.base.result.models.profile.ProfileEmailChangeResult
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileSecurityChangeEmailNavigationEvents : CoordinatorEvent() {

    data class EnterCode(val requestCode:String, val email:String) : ProfileSecurityChangeEmailNavigationEvents()

    data class FinishWithResult(val requestCode: String, val result: ProfileEmailChangeResult) :
        ProfileSecurityChangeEmailNavigationEvents()

}