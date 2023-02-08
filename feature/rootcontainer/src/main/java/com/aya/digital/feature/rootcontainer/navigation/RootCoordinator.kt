package com.aya.digital.feature.rootcontainer.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class RootCoordinator(
    private val navigationRouter: Router,
    context: Context,
    fragmentManager: FragmentManager,
    private val rootCoordinatorGraph: RootCoordinatorGraph
) : Coordinator {
    private val contextWeak: WeakReference<Context>
    private val fragmentManagerWeak: WeakReference<FragmentManager>

    init {
        this.contextWeak = WeakReference(context)
        this.fragmentManagerWeak = WeakReference(fragmentManager)
    }

    override fun consumeEvent(event: CoordinatorEvent) {
        rootCoordinatorGraph.processEvent(event,navigationRouter,fragmentManagerWeak)
    }
}
