package com.aya.digital.feature.auth.signup.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class SignUpSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : SignUpSideEffects()
}
