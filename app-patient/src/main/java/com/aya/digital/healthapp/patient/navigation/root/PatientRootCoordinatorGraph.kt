package com.aya.digital.healthapp.patient.navigation.root

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostScreen
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserScreen
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen
import com.aya.digital.feature.auth.signup.navigation.SignUpNavigationEvents
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogScreen
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class PatientRootCoordinatorGraph(context: Context) : RootCoordinatorGraph {
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
        when (event) {
            is RootContainerNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(AuthContainerScreen)
            }

            is RootContainerNavigationEvents.OpenDefaultScreenAuthorized -> {
                openRootScreen(StartScreen.HOME)
            }

            is RootContainerNavigationEvents.OpenBottomNavigationScreenDefault -> {
                openRootScreen(StartScreen.HOME)
            }
            is RootContainerNavigationEvents.EnterCode -> {
                navigationRouter.navigateTo(
                    CodeDialogScreen("ENTER_CODE", event.requestCode, event.email)
                )
            }

            is RootContainerNavigationEvents.SelectMultipleItems -> {
                navigationRouter.navigateTo(
                    MultiSelectChooserScreen(
                        event.requestCode,
                        event.selectedItems
                    )
                )
            }
            is CodeDialogNavigationEvents.FinishWithResult -> {
                navigationRouter.sendResult(event.requestCode, event.result)
                navigationRouter.exit()
            }

            is MultiSelectChooserNavigationEvents.FinishWithResult -> {
                navigationRouter.sendResult(event.requestCode, event.result)
                navigationRouter.exit()
            }
            is CodeDialogNavigationEvents.Exit -> {
                navigationRouter.exit()
            }
        }
    }
}