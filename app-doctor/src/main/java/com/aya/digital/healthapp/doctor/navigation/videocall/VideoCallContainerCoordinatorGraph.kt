package com.aya.digital.healthapp.doctor.navigation.videocall

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordNavigationEvents
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordOperationStateParam
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordScreen
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigationEvents
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostScreen
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenScreen
import com.aya.digital.core.feature.videocall.videocallservice.VideoService
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.rootcontainer.navigation.RootContainerScreen
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerNavigationEvents
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerScreen
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class VideoCallContainerCoordinatorGraph(context: Context) : RootCoordinatorGraph {
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
            is VideoContainerNavigationEvents.OpenVideoCall -> {
                navigationRouter.navigateTo(VideoCallScreenScreen(event.roomId))
            }
            is VideoCallScreenNavigationEvents.Back ->{
                navigationRouter.exit()
                navigationRouter.navigateTo(RootContainerScreen)
            }
            is CoordinatorEvent.Back -> {
                navigationRouter.exit()
                navigationRouter.navigateTo(RootContainerScreen)
            }
        }
    }
}