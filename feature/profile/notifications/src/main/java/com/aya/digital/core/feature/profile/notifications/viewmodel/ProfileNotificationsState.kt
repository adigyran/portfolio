package com.aya.digital.core.feature.profile.notifications.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileNotificationsState(
    val smsNotification: Boolean = false,
    val emailNotification: Boolean = false
) : BaseState
