package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.mappers.profile.CurrentProfileMapper
import com.aya.digital.core.data.mappers.profile.MessageMapper
import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.data.model.profile.Message
import com.aya.digital.core.network.model.response.MessageResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse

class MessageMapperImpl : MessageMapper() {
    override fun mapFrom(type: MessageResponse): Message =
        Message(type.message)
}