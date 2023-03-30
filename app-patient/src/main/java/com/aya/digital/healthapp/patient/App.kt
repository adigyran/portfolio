package com.aya.digital.healthapp.patient

import com.aya.digital.base.appbase.BaseApp
import com.aya.digital.core.di.coreDiModules
import com.aya.digital.core.navigation.graph.navigator.RootNavigationGraph
import com.aya.digital.healthapp.patient.di.patientAppDiModule
import com.aya.digital.healthapp.patient.navigation.root.PatientRootNavigationGraph
import net.openid.appauth.AuthorizationService
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.singleton
import timber.log.Timber

class App : BaseApp() {
    override val di: DI = DI.lazy {
        import(androidXModule(this@App))
        import(patientAppDiModule())
        bind<AuthorizationService>() with singleton { AuthorizationService(this@App)}
        coreDiModules().flatten().forEach { import(it) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}
