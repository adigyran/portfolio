package com.aya.digital.core.di

import com.aya.digital.core.datastore.di.dataStoreDiModule
import com.aya.digital.core.mappers.di.mappersDiModule
import com.aya.digital.core.repository.di.repositoriesDiModule

fun dataDiModules() = listOf(
    dataStoreDiModule(),
    repositoriesDiModule(),
    mappersDiModule()
)