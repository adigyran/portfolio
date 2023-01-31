package com.aya.digital.core.mappers.impl.profile

import com.aya.digital.core.data.mappers.profile.MessageMapper
import com.aya.digital.core.data.model.profile.Message
import com.aya.digital.core.network.model.response.MessageResponse

internal class MessageMapperImpl : MessageMapper() {
    override fun mapFrom(type: MessageResponse): Message =
        Message(type.message)
}