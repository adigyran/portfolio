package com.aya.digital.core.di

import com.aya.digital.core.mappers.di.mappersDiModule
import com.aya.digital.core.repository.di.repositoriesDiModule

fun coreDiModules() = listOf(
    networkDiModules(),
    dataDiModules()
)