package com.aya.digital.core.feature.profile.security.changephone.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ProfileSecurityChangePhoneState(
    val code: String? = null,
    val phone: String = "",
    val phoneError: String? = null
) : BaseState
