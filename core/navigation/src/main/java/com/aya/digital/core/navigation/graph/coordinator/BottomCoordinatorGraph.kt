package com.aya.digital.core.navigation.graph.coordinator

import androidx.fragment.app.FragmentManager
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

interface BottomCoordinatorGraph {
    fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    )
}