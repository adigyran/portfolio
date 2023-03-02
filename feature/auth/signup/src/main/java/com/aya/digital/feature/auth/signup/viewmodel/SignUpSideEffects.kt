package com.aya.digital.feature.auth.signup.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect

sealed class SignUpSideEffects:BaseSideEffect {
    object ListenForInsuranceEvent : SignUpSideEffects()
    object ListenForCodeEvent : SignUpSideEffects()
}
