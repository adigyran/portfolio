package com.aya.digital.core.feature.auth.restorepassword.navigation

import com.aya.digital.core.data.base.result.models.auth.PasswordRestoreResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class RestorePasswordNavigationEvents : CoordinatorEvent() {
    data class EnterCode(val requestCode:String, val email:String) : RestorePasswordNavigationEvents()
    data class FinishWithResult(val requestCode:String,val result: PasswordRestoreResultModel) : RestorePasswordNavigationEvents()

}