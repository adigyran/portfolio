package com.aya.digital.core.di.modules

import com.aya.digital.core.datastore.di.dataStoreDiModule
import com.aya.digital.core.domain.auth.di.authDomainDiModule
import com.aya.digital.core.repository.auth.authRepositoryDiModule
import com.aya.digital.core.repository.auth.dictionariesRepositoryDiModule
import com.aya.digital.core.repository.profile.profileRepositoryDiModule

fun repositoryDiModules() = listOf(
    authRepositoryDiModule(),
    profileRepositoryDiModule(),
    dictionariesRepositoryDiModule()
)