package com.aya.digital.core.feature.auth.signin.viewmodel

import android.content.Intent
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class SignInSideEffects:BaseSideEffect {

    data class OpenOAuthPage(val intent:Intent):SignInSideEffects()
    data class Error(val error:BaseViewModel.ErrorSideEffect) : SignInSideEffects()
}
