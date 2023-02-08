package com.aya.digital.core.navigation.graph.coordinator

import androidx.fragment.app.FragmentManager
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

interface RootCoordinatorGraph {
    fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        fragmentManagerWeak: WeakReference<FragmentManager>
    )
}