package com.aya.digital.core.mappers.doctors.di

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.mappers.profile.CurrentProfileMapperImpl
import com.aya.digital.core.mappers.profile.LoginResultMapperImpl
import com.aya.digital.core.mappers.profile.RoleMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun profileMappersDiModule() = DI.Module("profileMappersDiModule") {
    bind<LoginResultMapper>() with singleton { LoginResultMapperImpl() }
    bind<RoleMapper>() with singleton { RoleMapperImpl() }

    bind<CurrentProfileMapper>() with singleton { CurrentProfileMapperImpl(instance()) }

}