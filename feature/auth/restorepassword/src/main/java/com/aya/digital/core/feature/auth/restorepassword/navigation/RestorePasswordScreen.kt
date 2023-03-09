package com.aya.digital.core.feature.auth.restorepassword.navigation

import com.aya.digital.core.feature.auth.restorepassword.ui.RestorePasswordView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class RestorePasswordScreen(
    val requestCode: String,
    val operationState: RestorePasswordOperationStateParam
) : HealthAppFragmentScreen(fragmentCreator = {
    RestorePasswordView.getNewInstance(
        requestCode,
        operationState
    )
})
