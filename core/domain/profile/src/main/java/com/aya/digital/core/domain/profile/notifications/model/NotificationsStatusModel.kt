package com.aya.digital.core.domain.profile.notifications.model

import com.aya.digital.core.data.profile.NotificationsStatus

data class NotificationsStatusModel(val emailNotifications: Boolean, val smsNotifications: Boolean)
internal fun NotificationsStatus.toModel() = NotificationsStatusModel(this.email,this.phone)
