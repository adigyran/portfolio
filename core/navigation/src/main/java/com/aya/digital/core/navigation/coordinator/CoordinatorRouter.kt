package com.aya.digital.core.navigation.coordinator

import com.github.terrakok.cicerone.ResultListener

interface CoordinatorRouter {
    fun sendEvent(event: CoordinatorEvent)
    fun setResultListener(
        key: String,
        listener: ResultListener)
}