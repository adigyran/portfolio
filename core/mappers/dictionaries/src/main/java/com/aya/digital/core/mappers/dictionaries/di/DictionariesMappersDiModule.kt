package com.aya.digital.core.mappers.dictionaries.di

import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.mappers.dictionaries.InsuranceCompanyMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton


fun dictionariesMappersDiModule() = DI.Module("dictionariesMappersDiModule") {
    bind<InsuranceCompanyMapper>() with singleton { InsuranceCompanyMapperImpl() }
}