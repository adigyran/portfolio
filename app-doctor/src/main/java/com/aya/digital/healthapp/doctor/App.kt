package com.aya.digital.healthapp.doctor

import com.aya.digital.base.appbase.BaseApp
import com.aya.digital.core.di.coreDiModules
import com.aya.digital.healthapp.doctor.di.doctorAppDiModule
import net.openid.appauth.AuthorizationService
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.singleton
import timber.log.Timber
import timber.log.Timber.Forest.plant

class App : BaseApp() {
    override val di: DI = DI.lazy {
        import(androidXModule(this@App))
        import(doctorAppDiModule())
        bind<AuthorizationService>() with singleton { AuthorizationService(this@App)}
        coreDiModules().flatten().forEach { import(it) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}
