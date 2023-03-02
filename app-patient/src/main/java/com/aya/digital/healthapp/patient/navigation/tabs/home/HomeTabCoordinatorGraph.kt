package com.aya.digital.healthapp.patient.navigation.tabs.home

import com.aya.digital.core.feature.auth.signin.navigation.SignInNavigationEvents
import com.aya.digital.core.feature.auth.signin.navigation.SignInScreen
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserScreen
import com.aya.digital.core.feature.tabviews.home.navigation.HomeScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.auth.chooser.navigation.AuthChooserNavigationEvents
import com.aya.digital.feature.auth.chooser.navigation.AuthChooserScreen
import com.aya.digital.feature.auth.container.navigation.AuthContainerNavigationEvents
import com.aya.digital.feature.auth.signup.navigation.SignUpNavigationEvents
import com.aya.digital.feature.auth.signup.navigation.SignUpScreen
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogScreen
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.aya.digital.feature.tabs.home.navigation.HomeTabNavigationEvents
import com.github.terrakok.cicerone.Router

class HomeTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when (event) {
            HomeTabNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(HomeScreen)
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}