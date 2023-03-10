package com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileSecurityChangeEmailPhoneSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileSecurityChangeEmailPhoneSideEffects()
}
