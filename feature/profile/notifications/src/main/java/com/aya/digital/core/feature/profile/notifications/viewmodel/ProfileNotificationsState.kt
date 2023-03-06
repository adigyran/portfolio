package com.aya.digital.core.feature.profile.notifications.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileNotificationsState(val email: String = "",
                                     val emailError: String? = null,
                                     val password: String = "",
                                     val passwordError: String? = null
                       ) : BaseState
