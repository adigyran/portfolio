package com.aya.digital.core.mappers.dictionaries.di

import com.aya.digital.core.data.dictionaries.mappers.CityMapper
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.dictionaries.mappers.LanguageMapper
import com.aya.digital.core.data.dictionaries.mappers.SpecialityMapper
import com.aya.digital.core.mappers.dictionaries.CityMapperImpl
import com.aya.digital.core.mappers.dictionaries.InsuranceCompanyMapperImpl
import com.aya.digital.core.mappers.dictionaries.LanguageMapperImpl
import com.aya.digital.core.mappers.dictionaries.SpecialityMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton


fun dictionariesMappersDiModule() = DI.Module("dictionariesMappersDiModule") {
    bind<InsuranceCompanyMapper>() with singleton { InsuranceCompanyMapperImpl() }
    bind<SpecialityMapper>() with singleton { SpecialityMapperImpl() }
    bind<CityMapper>() with singleton { CityMapperImpl() }
    bind<LanguageMapper>() with singleton { LanguageMapperImpl() }



}