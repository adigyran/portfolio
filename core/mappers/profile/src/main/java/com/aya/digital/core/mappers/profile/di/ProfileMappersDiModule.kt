package com.aya.digital.core.mappers.doctors.di

import com.aya.digital.core.data.mappers.profile.InsurancePolicyMapper
import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.mappers.*
import com.aya.digital.core.mappers.profile.*
import com.aya.digital.core.mappers.profile.AvatarMapperImpl
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
    bind<AvatarMapper>() with singleton { AvatarMapperImpl() }
    bind<UserKeyResultMapper>() with singleton { UserKeyResultMapperImpl() }
    bind<InsurancePolicyMapper>() with singleton { InsurancePolicyMapperImpl() }
    bind<EmergencyContactMapper>() with singleton { EmergencyContactMapperImpl() }
    bind<ImageUploadResultMapper>() with singleton { ImageUploadResultMapperImpl() }



    bind<CurrentProfileMapper>() with singleton { CurrentProfileMapperImpl(instance(),instance()) }

}