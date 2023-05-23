package com.aya.digital.core.util.di

import com.aya.digital.core.util.datetime.DateTimeFormatters
import com.aya.digital.core.util.datetime.DateTimeUtils
import com.aya.digital.core.util.datetime.DateTimeUtilsImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun utilDiModule() = DI.Module("utilDiModule") {
    bind<DateTimeFormatters>() with singleton { DateTimeFormatters(instance()) }
    bind<DateTimeUtils>() with singleton { DateTimeUtilsImpl(instance(),instance()) }

}