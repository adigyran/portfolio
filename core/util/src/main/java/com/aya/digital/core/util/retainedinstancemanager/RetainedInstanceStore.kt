package com.aya.digital.core.util.retainedinstancemanager

import com.aya.digital.core.util.retainedinstancemanager.exceptions.InstanceNotFoundException


internal class RetainedInstanceStore<T> {
    private val instances = mutableMapOf<String, T>()

    fun isExist(key: String): Boolean = instances.containsKey(key)

    fun add(key: String, component: T) {
        instances[key] = component
    }

    fun get(key: String): T = instances[key] ?: throw InstanceNotFoundException()

    fun remove(key: String) {
        instances.remove(key)
    }
}