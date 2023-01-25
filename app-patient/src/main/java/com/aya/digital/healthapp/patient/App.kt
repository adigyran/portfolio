package com.aya.digital.healthapp.patient

import com.aya.digital.base.appbase.BaseApp
import com.aya.digital.core.di.coreDiModules
import com.aya.digital.healthapp.patient.di.patientAppDiModule
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

class App : BaseApp() {
    override val di: DI = DI.lazy {
        import(androidXModule(this@App))
        import(patientAppDiModule())
        coreDiModules().flatten().forEach { import(it) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}