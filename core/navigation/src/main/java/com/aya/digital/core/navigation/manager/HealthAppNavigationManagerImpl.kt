package com.aya.digital.core.navigation.manager

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.screen.HealthAppScreen
import java.util.*

class HealthAppNavigationManagerImpl : HealthAppNavigationManager {

    private val screensMap = hashMapOf<String, HealthAppScreen>()
    private val eventsMap = hashMapOf<String, CoordinatorEvent>()

    override fun putScreen(screen: HealthAppScreen) {
        screensMap.put(screen.screenTag, screen)
    }

    override fun putEvent(event: CoordinatorEvent) {
        event.tag?.let {
            eventsMap.put(event.tag, event)
        }
    }

    override fun getEventByTag(tag: String): Optional<CoordinatorEvent> {
        TODO("Not yet implemented")
    }

    override fun processEvent(event: CoordinatorEvent) {
    }

}