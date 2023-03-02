package com.aya.digital.core.di.modules

import com.aya.digital.core.mappers.dictionaries.di.dictionariesMappersDiModule
import com.aya.digital.core.mappers.doctors.di.profileMappersDiModule

fun mappersDiModules() = listOf(
    profileMappersDiModule(),
    dictionariesMappersDiModule()
)