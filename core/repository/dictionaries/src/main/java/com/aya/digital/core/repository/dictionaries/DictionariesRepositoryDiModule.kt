package com.aya.digital.core.repository.auth

import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun dictionariesRepositoryDiModule() = DI.Module("dictionariesRepositoryDiModule") {
    bind<DictionariesRepository>() with singleton { DictionariesRepositoryImpl(instance(),instance()) }
}