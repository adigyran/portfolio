package com.aya.digital.core.navigation.coordinator

interface Coordinator {
    fun consumeEvent(event: CoordinatorEvent)
}