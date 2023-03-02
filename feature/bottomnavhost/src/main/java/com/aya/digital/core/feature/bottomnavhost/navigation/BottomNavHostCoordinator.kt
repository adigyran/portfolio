package com.aya.digital.core.feature.bottomnavhost.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.coordinator.BottomCoordinatorGraph
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class BottomNavHostCoordinator(
    private val navigationRouter: Router,
    private val parentCoordinatorRouter: CoordinatorRouter,
    context: Context,
    private val bottomCoordinatorGraph: BottomCoordinatorGraph
) : Coordinator {

    private val contextWeak: WeakReference<Context>
    init {
        this.contextWeak = WeakReference(context)
    }
    override fun consumeEvent(event: CoordinatorEvent) {
        bottomCoordinatorGraph.processEvent(event, navigationRouter,parentCoordinatorRouter)
    }
}