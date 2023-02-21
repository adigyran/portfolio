package com.aya.digital.core.feature.auth.signin.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInState(val stub: Boolean = false) : BaseState
