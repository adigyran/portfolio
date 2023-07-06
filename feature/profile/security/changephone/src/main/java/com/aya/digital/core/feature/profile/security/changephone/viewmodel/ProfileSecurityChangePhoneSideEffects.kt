package com.aya.digital.core.feature.profile.security.changephone.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileSecurityChangePhoneSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileSecurityChangePhoneSideEffects()
}
