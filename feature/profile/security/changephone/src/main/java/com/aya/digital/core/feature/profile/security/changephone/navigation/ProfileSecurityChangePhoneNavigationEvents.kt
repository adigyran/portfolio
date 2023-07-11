package com.aya.digital.core.feature.profile.security.changephone.navigation

import com.aya.digital.core.data.base.result.models.profile.ProfileEmailChangeResult
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileSecurityChangePhoneNavigationEvents : CoordinatorEvent() {

    data class EnterCode(val requestCode:String, val phone:String) : ProfileSecurityChangePhoneNavigationEvents()

    data class FinishWithResult(val requestCode: String, val result: ProfileEmailChangeResult) :
        ProfileSecurityChangePhoneNavigationEvents()

}