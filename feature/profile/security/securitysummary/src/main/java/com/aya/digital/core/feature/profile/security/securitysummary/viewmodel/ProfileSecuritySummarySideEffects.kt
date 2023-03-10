package com.aya.digital.core.feature.profile.security.securitysummary.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileSecuritySummarySideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileSecuritySummarySideEffects()
}
