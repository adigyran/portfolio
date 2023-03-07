package com.aya.digital.core.feature.auth.signin.navigation

import com.aya.digital.core.feature.auth.signin.ui.RestorePasswordView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class RestorePasswordScreen(
    val requestCode: String,
    val operationState: RestorePasswordView.Param.RestorePasswordOperationStateParam
) : HealthAppFragmentScreen(fragmentCreator = {
    RestorePasswordView.getNewInstance(
        requestCode,
        operationState
    )
})
