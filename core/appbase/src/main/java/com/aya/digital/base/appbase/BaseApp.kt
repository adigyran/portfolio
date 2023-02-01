package com.aya.digital.base.appbase

import android.app.Application
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.dibase.scopes.CustomScopesManager
import com.aya.digital.core.util.logging.HealthAppDebugTree
import org.kodein.di.DIAware
import timber.log.Timber


abstract class BaseApp : Application(), DIAware {

    override fun onCreate() {
        super.onCreate()

        appInstance = this

        KodeinInjectionManager.instance.init(this)
        CustomScopesManager.init(this)
        Timber.plant(HealthAppDebugTree())
    }

    companion object {
        lateinit var appInstance: BaseApp
            private set
    }
}
