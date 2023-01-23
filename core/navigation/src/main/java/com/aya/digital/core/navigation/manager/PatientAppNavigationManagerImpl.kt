package com.aya.digital.core.navigation.manager

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.screen.PatientAppScreen
import java.util.*

class PatientAppNavigationManagerImpl : PatientAppNavigationManager {

    private val screensMap = hashMapOf<String, PatientAppScreen>()
    private val eventsMap = hashMapOf<String, CoordinatorEvent>()

    override fun putScreen(screen: PatientAppScreen) {
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