package com.aya.digital.core.feature.profile.security.securitysummary.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileSecuritySummaryState(
    val email: String? = null, val phone: String? = null, val password: String? = null
) : BaseState
