package com.aya.digital.core.feature.profile.security.changepassword.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ProfileSecurityChangePasswordState(
    val currentPassword: String = "",
    val currentPasswordError: Int? = null,
    val newPassword: String = "",
    val newPasswordError: Int? = null,
    val newRepeatPassword: String = "",
    val newRepeatPasswordError: Int? = null
) : BaseState
