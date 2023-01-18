package com.aya.digital.core.di


fun coreDiModules() = listOf(
    networkDiModules(),
    dataDiModules()
)