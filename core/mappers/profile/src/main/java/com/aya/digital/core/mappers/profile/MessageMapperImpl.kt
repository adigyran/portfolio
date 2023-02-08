package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.mappers.profile.MessageMapper
import com.aya.digital.core.data.profile.Message
import com.aya.digital.core.network.model.response.MessageResponse

internal class MessageMapperImpl : MessageMapper() {
    override fun mapFrom(type: MessageResponse): Message =
        Message(type.message)
}