package com.aya.digital.core.feature.auth.restorepassword.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class RestorePasswordSideEffects:BaseSideEffect {
    data class Error(val error: BaseViewModel.ErrorSideEffect) : RestorePasswordSideEffects()
}