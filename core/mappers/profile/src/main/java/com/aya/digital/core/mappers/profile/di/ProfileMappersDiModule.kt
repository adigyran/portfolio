package com.aya.digital.core.mappers.doctors.di

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.mappers.profile.LoginResultMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton


fun profileMappersDiModule() = DI.Module("profileMappersDiModule") {
    bind<LoginResultMapper>() with singleton { LoginResultMapperImpl() }
}