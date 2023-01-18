package com.aya.digital.core.mappers.di

import com.aya.digital.core.data.mappers.preferences.AuthUserDataMapper
import com.aya.digital.core.data.mappers.profile.CurrentProfileMapper
import com.aya.digital.core.data.mappers.profile.MessageMapper
import com.aya.digital.core.data.mappers.profile.RoleMapper
import com.aya.digital.core.mappers.preferences.AuthUserDataMapperImpl
import com.aya.digital.core.mappers.profile.CurrentProfileMapperImpl
import com.aya.digital.core.mappers.profile.MessageMapperImpl
import com.aya.digital.core.mappers.profile.RoleMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

fun mappersDiModule() = DI.Module("mappersDiModule") {
    bind<MessageMapper>() with provider { MessageMapperImpl() }
    bind<RoleMapper>() with provider { RoleMapperImpl() }
    bind<CurrentProfileMapper>() with provider { CurrentProfileMapperImpl(instance()) }
    bind<AuthUserDataMapper>() with provider { AuthUserDataMapperImpl() }
}
