package com.aya.digital.feature.auth.signup.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpState(val stub: Boolean = false) : BaseState
