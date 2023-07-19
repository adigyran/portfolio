package com.aya.digital.core.di

import com.aya.digital.core.di.modules.dataBaseDiModules
import com.aya.digital.core.di.modules.dataDiModules
import com.aya.digital.core.di.modules.domainDiModules
import com.aya.digital.core.di.modules.mappersDiModules
import com.aya.digital.core.di.modules.navigationDiModules
import com.aya.digital.core.di.modules.networkDataDiModules
import com.aya.digital.core.di.modules.repositoryDiModules
import com.aya.digital.core.di.modules.securityDiModules
import com.aya.digital.core.util.di.utilDiModule


fun coreDiModules() = listOf(
    networkDiModules(),
    networkDataDiModules(),
    navigationDiModules(),
    securityDiModules(),
    dataDiModules(),
    dataBaseDiModules(),
    domainDiModules(),
    mappersDiModules(),
    repositoryDiModules(),
    listOf(utilDiModule())
)
