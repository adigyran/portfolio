package com.aya.digital.core.navigation.manager

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.screen.HealthAppScreen
import java.util.*

interface HealthAppNavigationManager {

    fun putScreen(screen: HealthAppScreen)

    fun putEvent(event: CoordinatorEvent)

    fun getEventByTag(tag: String): Optional<CoordinatorEvent>

    fun processEvent(event: CoordinatorEvent)
}