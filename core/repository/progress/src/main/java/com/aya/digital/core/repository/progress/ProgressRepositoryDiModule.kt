package com.aya.digital.core.repository.progress

import com.aya.digital.core.data.progress.repository.ProgressRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun progressRepositoryDiModule() = DI.Module("progressRepositoryDiModule") {
    bind<ProgressRepository>() with singleton { ProgressRepositoryImpl() }
}