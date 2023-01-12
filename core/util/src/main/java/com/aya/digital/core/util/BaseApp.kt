package com.aya.digital.core.util

import androidx.multidex.MultiDexApplication
import net.danlew.android.joda.JodaTimeAndroid
import org.kodein.di.DIAware
import ru.ivan.core.di.KodeinInjectionManager
import ru.ivan.core.di.scopes.CustomScopesManager

abstract class BaseApp : MultiDexApplication(), DIAware {

    override fun onCreate() {
        super.onCreate()

        appInstance = this

        KodeinInjectionManager.instance.init(this)
        CustomScopesManager.init(this)

        JodaTimeAndroid.init(this)
    }

    companion object {
        lateinit var appInstance: BaseApp
            private set
    }
}