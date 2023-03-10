package com.aya.digital.core.feature.auth.signin.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class SignInSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : SignInSideEffects()
}
