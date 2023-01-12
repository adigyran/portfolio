package com.aya.digital.core.di

import android.app.Application
import com.aya.digital.core.util.retainedinstancemanager.IHasRetainedInstance
import com.aya.digital.core.util.retainedinstancemanager.RetainedInstanceManager
import org.kodein.di.DI


class KodeinInjectionManager {
    private val injectionManager =
        RetainedInstanceManager<DI>()

    companion object {
        @JvmStatic
        val instance = KodeinInjectionManager()
    }

    fun init(app: Application) = injectionManager.init(app)

    fun bindKodein(owner: IHasRetainedInstance<DI>) = injectionManager.bindKodein(owner)
}