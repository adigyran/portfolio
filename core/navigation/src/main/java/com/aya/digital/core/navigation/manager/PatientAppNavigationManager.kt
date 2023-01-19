package com.aya.digital.core.navigation.manager

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.screen.PatientAppScreen
import java.util.*

interface PatientAppNavigationManager {

    fun putScreen(screen: PatientAppScreen)

    fun putEvent(event: CoordinatorEvent)

    fun getEventByTag(tag: String): Optional<CoordinatorEvent>

    fun processEvent(event: CoordinatorEvent)
}