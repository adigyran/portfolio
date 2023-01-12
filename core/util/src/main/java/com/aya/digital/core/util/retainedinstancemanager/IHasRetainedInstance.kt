package com.aya.digital.core.util.retainedinstancemanager

interface IHasRetainedInstance<T> {
    fun createRetainedInstance(): T
}