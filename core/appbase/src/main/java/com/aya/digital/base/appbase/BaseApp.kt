package com.aya.digital.base.appbase

import android.app.Application
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.dibase.scopes.CustomScopesManager
import org.kodein.di.DIAware


abstract class BaseApp : Application(), DIAware {

    override fun onCreate() {
        super.onCreate()

        appInstance = this

        KodeinInjectionManager.instance.init(this)
        CustomScopesManager.init(this)
    }

    companion object {
        lateinit var appInstance: BaseApp
            private set
    }
}