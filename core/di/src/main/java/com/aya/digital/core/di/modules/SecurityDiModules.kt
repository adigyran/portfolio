package com.aya.digital.core.di.modules

import com.aya.digital.core.security.di.securityDiModule

fun securityDiModules() = listOf(
    securityDiModule()
)