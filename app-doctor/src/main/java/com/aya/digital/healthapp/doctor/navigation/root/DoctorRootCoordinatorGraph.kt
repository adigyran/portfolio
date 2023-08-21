package com.aya.digital.healthapp.doctor.navigation.root

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostScreen
import com.aya.digital.core.feature.choosers.multiselect.navigation.SelectWithSearchNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.navigation.SelectWithSearchScreen
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen
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
        fun openRootScreen(startScreen: StartScreen) =
            navigationRouter.newRootScreen(BottomNavHostScreen(startScreen))

        when(event)
        {
            is RootContainerNavigationEvents.OpenDefaultScreen ->
            {
                navigationRouter.newRootScreen(AuthContainerScreen)
            }
            is RootContainerNavigationEvents.OpenDefaultScreenAuthorized -> {
                openRootScreen(StartScreen.APPOINTMENTS)
            }

            is RootContainerNavigationEvents.OpenBottomNavigationScreenDefault -> {
                openRootScreen(StartScreen.APPOINTMENTS)
            }

            is RootContainerNavigationEvents.SelectMultipleItems -> {
                navigationRouter.navigateTo(
                    SelectWithSearchScreen(
                        requestCode = event.requestCode,
                        isMultiChoose = true,
                        selectedItems = event.selectedItems
                    )
                )
            }

            is RootContainerNavigationEvents.SelectSingleItem -> {
                navigationRouter.navigateTo(
                    SelectWithSearchScreen(
                        requestCode = event.requestCode,
                        isMultiChoose = false,
                        selectedItems = event.selectedItem?.let { setOf(it) } ?: setOf()
                    )
                )
            }
            is SelectWithSearchNavigationEvents.FinishWithResult -> {
                navigationRouter.exit()
                navigationRouter.sendResult(event.requestCode, event.result)
            }
        }
    }
}