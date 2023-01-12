package com.aya.digital.patientapp

import com.aya.digital.base.appbase.BaseApp
import org.kodein.di.DI
import org.kodein.di.android.androidCoreModule
import org.kodein.di.android.x.BuildConfig
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import timber.log.Timber
import timber.log.Timber.Forest.plant

class App : BaseApp() {
    override val di: DI = DI.lazy {
        import(androidXModule(this@App))
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            plant(Timber.DebugTree())
    }
}