package com.aya.digital.core.feature.profile.security.changeemail.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileSecurityChangeEmailSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileSecurityChangeEmailSideEffects()
}
