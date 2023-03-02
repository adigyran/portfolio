package com.aya.digital.core.navigation.coordinator

import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.ResultListener
import com.github.terrakok.cicerone.Router
import java.util.*

/**
 * TODO
 *
 */
class CoordinatorHolder : CoordinatorRouter {
    private var coordinator: Coordinator? = null
    private var router: BaseRouter? = null

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

    fun setRouter(router: BaseRouter?)
    {
        this.router = router
    }

    fun removeCoordinator() {
        coordinator = null
    }

    fun removeRouter() {
        router = null
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

    override fun setResultListener(key: String, listener: ResultListener) {
        router?.setResultListener(key,listener)
    }

}