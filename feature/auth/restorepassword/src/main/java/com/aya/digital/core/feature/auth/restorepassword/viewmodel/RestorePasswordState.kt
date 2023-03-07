package com.aya.digital.core.feature.auth.restorepassword.viewmodel

import com.aya.digital.core.feature.auth.restorepassword.viewmodel.mode.RestorePasswordOperationState
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestorePasswordState(
    val operationState: RestorePasswordOperationState,
    val code:String? = null,
    val email: String = "",
    val emailError: String? = null,
    val passwordNew: String = "",
    val passwordNewError: String? = null,
    val passwordNewRepeat: String = "",
    val passwordNewErrorRepeat: String? = null
) : BaseState
