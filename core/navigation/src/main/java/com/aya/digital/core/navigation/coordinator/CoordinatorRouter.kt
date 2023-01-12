package com.aya.digital.core.navigation.coordinator

interface CoordinatorRouter {
    fun sendEvent(event: CoordinatorEvent)
}