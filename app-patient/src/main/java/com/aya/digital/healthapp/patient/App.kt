package com.aya.digital.healthapp.patient

import com.aya.digital.base.appbase.BaseApp
import com.aya.digital.core.di.coreDiModules
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

class App : BaseApp() {
    override val di: DI = DI.lazy {
        import(androidXModule(this@App))
        coreDiModules().flatten().forEach { import(it) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}