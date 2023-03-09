package com.aya.digital.core.feature.profile.generalinfo.edit.navigation

import com.aya.digital.core.data.base.result.models.auth.PasswordRestoreResultModel
import com.aya.digital.core.data.base.result.models.profile.ProfileSaveResult
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileGeneralInfoEditNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode: String, val result: ProfileSaveResult) :
        ProfileGeneralInfoEditNavigationEvents()

}