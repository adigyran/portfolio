package com.aya.digital.core.util.retainedinstancemanager

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import com.aya.digital.core.util.retainedinstancemanager.helpers.ActivityLifecycleHelper

class RetainedInstanceManager<T> {
    private val store = RetainedInstanceStore<T>()

    fun init(app: Application) = app.registerActivityLifecycleCallbacks(
        ActivityLifecycleHelper(
            onActivityDestroyed = { activity ->
                val uuid = (activity as? IdProvider)?.getUuid() ?: return@ActivityLifecycleHelper
                Log.d(
                    RetainedInstanceManager::class.java.simpleName,
                    "Instance for activity with key=$uuid removing."
                )
                store.remove(uuid)
            },
            onFragmentDestroyed = { fragment ->
                val uuid = (fragment as? IdProvider)?.getUuid() ?: return@ActivityLifecycleHelper
                Log.d(
                    RetainedInstanceManager::class.java.simpleName,
                    "Instance for fragment with key=$uuid removing."
                )
                store.remove(uuid)
            })
    )

    fun bindKodein(owner: IHasRetainedInstance<T>): T {
        val key = when (owner) {
            is IdProvider -> owner.getUuid()
            else -> throw RuntimeException("Owner must be IdProvider")
        }

        return when {
            store.isExist(key) -> {
                Log.d(
                    RetainedInstanceManager::class.java.simpleName,
                    "Instance for ${if (owner is Fragment) "fragment" else "activity"} with key=${key} getting from cache."
                )
                store.get(key)
            }
            else -> {
                Log.d(
                    RetainedInstanceManager::class.java.simpleName,
                    "Instance for ${if (owner is Fragment) "fragment" else "activity"} with key=${key} creating."
                )
                owner.createRetainedInstance().also { store.add(key, it) }
            }
        }
    }
}