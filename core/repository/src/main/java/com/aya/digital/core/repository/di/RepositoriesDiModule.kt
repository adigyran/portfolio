package com.aya.digital.core.repository.di

import com.aya.digital.core.data.mappers.profile.CurrentProfileMapper
import com.aya.digital.core.data.mappers.profile.MessageMapper
import com.aya.digital.core.data.mappers.profile.RoleMapper
import com.aya.digital.core.data.repositories.profile.AuthRepository
import com.aya.digital.core.mappers.profile.CurrentProfileMapperImpl
import com.aya.digital.core.mappers.profile.MessageMapperImpl
import com.aya.digital.core.mappers.profile.RoleMapperImpl
import com.aya.digital.core.repository.profile.AuthRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

fun repositoriesDiModule() = DI.Module("repositoriesDiModule") {
    bind<AuthRepository>() {provider { AuthRepositoryImpl(instance()) }}
}
