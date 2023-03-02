package com.aya.digital.healthapp.doctor.navigation.root

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.aya.digital.feature.rootcontainer.ui.RootView
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class DoctorRootCoordinatorGraph(context: Context) : RootCoordinatorGraph {
    private val contextWeak: WeakReference<Context>

    init {
        this.contextWeak = WeakReference(context)
    }
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        fragmentManagerWeak: WeakReference<FragmentManager>
    ) {
        when(event)
        {
            is RootContainerNavigationEvents.OpenDefaultScreen ->
            {
               // navigationRouter.newRootScreen(AuthContainerScreen)
            }
        }
    }
}