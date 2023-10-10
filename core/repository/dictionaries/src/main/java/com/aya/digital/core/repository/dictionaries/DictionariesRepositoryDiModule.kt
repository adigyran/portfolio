package com.aya.digital.core.repository.dictionaries

import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.repository.dictionaries.DictionariesRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun dictionariesRepositoryDiModule() = DI.Module("dictionariesRepositoryDiModule") {
    bind<DictionariesRepository>() with singleton {
        DictionariesRepositoryImpl(
            dictionariesDataSource = instance(),
            insuranceMapper = instance(),
            specialityMapper = instance(),
            cityMapper = instance(),
            languageMapper = instance(),
            medicalDegreeMapper = instance(),
            emergencyContactTypeMapper = instance()
        )
    }
}