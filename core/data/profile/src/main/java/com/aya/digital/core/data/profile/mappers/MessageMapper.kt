package com.aya.digital.core.data.mappers.profile

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.Message
import com.aya.digital.core.network.model.response.MessageResponse

abstract class MessageMapper :
    EntityMapper<MessageResponse, Message>