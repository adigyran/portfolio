package com.aya.digital.core.domain.profile.di

import com.aya.digital.core.domain.root.progress.ListenForProgressUseCase
import com.aya.digital.core.domain.root.progress.impl.ListenForProgressUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun rootDomainDiModule() = DI.Module("rootDomainDiModule") {
    bind<ListenForProgressUseCase>() with singleton { ListenForProgressUseCaseImpl(instance()) }
}