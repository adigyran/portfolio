package com.aya.digital.healthapp.patient.navigation.bottom

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigationEvents
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostScreen
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.coordinator.BottomCoordinatorGraph
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.aya.digital.feature.tabs.appointments.navigation.AppointmentsTabScreen
import com.aya.digital.feature.tabs.doctorsearch.navigation.DoctorSearchTabScreen
import com.aya.digital.feature.tabs.home.navigation.HomeTabScreen
import com.aya.digital.feature.tabs.profile.navigation.ProfileTabScreen
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class BottomNavigationCoordinatorGraphImpl(context: Context) : BottomCoordinatorGraph {
    private val contextWeak: WeakReference<Context>

    init {
        this.contextWeak = WeakReference(context)
    }

    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when(event)
        {
            is BottomNavHostNavigationEvents.OpenHome-> {
                navigationRouter.replaceScreen(HomeTabScreen)

            }
            is BottomNavHostNavigationEvents.OpenDoctorSearch-> {
                navigationRouter.replaceScreen(DoctorSearchTabScreen)
            }
            is BottomNavHostNavigationEvents.OpenAppointments-> {
                navigationRouter.replaceScreen(AppointmentsTabScreen)

            }
            is BottomNavHostNavigationEvents.OpenProfile-> {
                navigationRouter.replaceScreen(ProfileTabScreen)
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }
    }
}