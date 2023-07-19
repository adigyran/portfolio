package com.aya.digital.core.di.modules

import com.aya.digital.core.datastore.di.dataStoreDiModule

fun dataDiModules() = listOf(
    dataStoreDiModule()
)