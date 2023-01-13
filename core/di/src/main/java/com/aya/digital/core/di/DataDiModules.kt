package com.aya.digital.core.di

import com.aya.digital.core.datasource.servicesModule
import com.aya.digital.core.mappers.di.mappersDiModule
import com.aya.digital.core.network.di.okHttpClientModule
import com.aya.digital.core.network.di.retrofitModule
import com.aya.digital.core.network.interceptorsModule
import com.aya.digital.core.network.moshiConverterFactoryModule
import com.aya.digital.core.network.moshiModule
import com.aya.digital.core.network.rxJavaCallAdapterModule
import com.aya.digital.core.repository.di.repositoriesDiModule

fun dataDiModules() = listOf(
    repositoriesDiModule(),
    mappersDiModule()
)