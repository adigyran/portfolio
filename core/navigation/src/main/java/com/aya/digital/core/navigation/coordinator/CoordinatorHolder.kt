package com.aya.digital.core.navigation.coordinator

import java.util.*

/**
 * TODO
 *
 */
class CoordinatorHolder : CoordinatorRouter {
    private var coordinator: Coordinator? = null
    private val pendingEvents = LinkedList<CoordinatorEvent>()

    /**
     * TODO
     *
     * @param coordinator
     */
    fun setCoordinator(coordinator: Coordinator?) {
        this.coordinator = coordinator
        while (!pendingEvents.isEmpty()) {
            if (coordinator != null) {
                sendEvent(pendingEvents.poll())
            } else {
                break
            }
        }
    }

    fun removeCoordinator() {
        coordinator = null
    }

    /**
     * TODO
     *
     * @param event
     */
    override fun sendEvent(event: CoordinatorEvent) {
        if (coordinator != null) {
            coordinator!!.consumeEvent(event)
        } else {
            pendingEvents.add(event)
        }
    }
}