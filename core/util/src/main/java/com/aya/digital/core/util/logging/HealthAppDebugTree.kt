package com.aya.digital.core.util.logging

import timber.log.Timber

/**
 *
 */
class HealthAppDebugTree : Timber.DebugTree() {

    private val hyperLink : String
        get() = Thread.currentThread().stackTrace.let { elements: Array<StackTraceElement> ->
            val index = 7 // most relevant StackTraceElement index that actually prints to logcat
            elements[index].let {
                "(${it.fileName}:${it.lineNumber}) "
            }
        }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, hyperLink + message, t)
    }
}