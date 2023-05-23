package com.aya.digital.core.di

import com.aya.digital.core.database.di.dataBaseDiModule
import com.aya.digital.core.datastore.di.dataStoreDiModule

fun dataBaseDiModules() = listOf(
    dataBaseDiModule()
)