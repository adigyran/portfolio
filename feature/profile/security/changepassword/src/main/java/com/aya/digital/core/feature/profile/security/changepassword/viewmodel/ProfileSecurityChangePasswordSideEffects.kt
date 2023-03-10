package com.aya.digital.core.feature.profile.security.changepassword.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileSecurityChangePasswordSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileSecurityChangePasswordSideEffects()
}
