package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.Message
import com.aya.digital.core.data.profile.NotificationsStatus
import com.aya.digital.core.network.model.response.MessageResponse
import com.aya.digital.core.network.model.response.profile.NotificationSettingsResponse

abstract class NotificationsStatusMapper :
    EntityMapper<NotificationSettingsResponse, NotificationsStatus>