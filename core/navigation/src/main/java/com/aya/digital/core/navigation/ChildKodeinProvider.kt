package com.aya.digital.core.navigation

import org.kodein.di.DI

fun interface ChildKodeinProvider {
    fun getChildKodein(): DI
}