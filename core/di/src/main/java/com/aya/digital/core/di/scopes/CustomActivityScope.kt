package com.aya.digital.core.di.scopes

import android.app.Activity
import android.util.Log
import org.kodein.di.bindings.Scope
import org.kodein.di.bindings.ScopeRegistry
import org.kodein.di.bindings.StandardScopeRegistry
import ru.ivan.core.util.retainedinstancemanager.IdProvider

object CustomActivityScope : Scope<Activity> {
    private val scopeRegistryMap: HashMap<String, StandardScopeRegistry> = hashMapOf()

    override fun getRegistry(context: Activity): ScopeRegistry {
        val uuid = (context as? IdProvider)?.getUuid()
            ?: throw RuntimeException("Uuid must not be null")

        val fragmentScopeRegistry = scopeRegistryMap[uuid]

        return fragmentScopeRegistry as? ScopeRegistry
            ?: StandardScopeRegistry().also {
                scopeRegistryMap[uuid] = it
            }
    }

    fun clearScope(context: Activity) {
        val uuid = (context as? IdProvider)?.getUuid() ?: return

        Log.d(CustomActivityScope::class.java.simpleName, "Scope with key=$uuid clearing.")

        scopeRegistryMap[uuid]?.clear()
        scopeRegistryMap.remove(uuid)
    }
}