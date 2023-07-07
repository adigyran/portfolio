package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.data.profile.NotificationsStatus
import com.aya.digital.core.data.profile.mappers.NotificationsStatusMapper
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.profile.NotificationSettingsResponse

internal class NotificationsStatusMapperImpl : NotificationsStatusMapper() {
    override fun mapFrom(type: NotificationSettingsResponse): NotificationsStatus =
        NotificationsStatus(type.isEmail,type.isPhone)

}