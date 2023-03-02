package com.aya.digital.core.mappers.dictionaries.di

import com.aya.digital.core.data.profile.mappers.InsuranceMapper
import com.aya.digital.core.mappers.dictionaries.InsuranceMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton


fun dictionariesMappersDiModule() = DI.Module("dictionariesMappersDiModule") {
    bind<InsuranceMapper>() with singleton { InsuranceMapperImpl() }
}