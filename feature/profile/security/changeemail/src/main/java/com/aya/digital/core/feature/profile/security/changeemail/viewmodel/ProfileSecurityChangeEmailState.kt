package com.aya.digital.core.feature.profile.security.changeemail.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ProfileSecurityChangeEmailState(
    val code: String? = null,
    val email: String = "",
    val emailError: Int? = null
) : BaseState
