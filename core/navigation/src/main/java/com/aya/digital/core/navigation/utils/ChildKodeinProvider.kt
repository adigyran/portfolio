package com.aya.digital.core.navigation.utils

import org.kodein.di.DI

fun interface ChildKodeinProvider {
    fun getChildKodein(): DI
}