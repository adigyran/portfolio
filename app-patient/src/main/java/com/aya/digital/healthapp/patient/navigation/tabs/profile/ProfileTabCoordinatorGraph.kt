package com.aya.digital.healthapp.patient.navigation.tabs.profile

import com.aya.digital.core.feature.auth.signin.navigation.SignInNavigationEvents
import com.aya.digital.core.feature.auth.signin.navigation.SignInScreen
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserScreen
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListScreen
import com.aya.digital.core.feature.profile.generalinfo.edit.navigation.ProfileGeneralInfoEditScreen
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewScreen
import com.aya.digital.core.feature.profile.security.securitysummary.navigation.ProfileSecuritySummaryScreen
import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileNavigationEvents
import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileScreen
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
import com.aya.digital.feature.tabs.profile.navigation.ProfileTabNavigationEvents
import com.github.terrakok.cicerone.Router

class ProfileTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when (event) {
            ProfileTabNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(ProfileScreen)
            }
            ProfileNavigationEvents.OpenProfileGeneralInfo -> {
                navigationRouter.navigateTo(ProfileGeneralInfoViewScreen)
            }
            ProfileNavigationEvents.OpenProfileEmergencyContact -> {
                navigationRouter.navigateTo(ProfileGeneralInfoViewScreen)
            }
            ProfileNavigationEvents.OpenProfileInsurance -> {
                navigationRouter.navigateTo(ProfileInsuranceListScreen)
            }
            ProfileNavigationEvents.OpenProfileSecurity -> {
                navigationRouter.navigateTo(ProfileSecuritySummaryScreen)
            }
            ProfileNavigationEvents.OpenProfileNotification -> {

            }
            ProfileGeneralInfoViewNavigationEvents.EditProfile -> {
                navigationRouter.navigateTo(ProfileGeneralInfoEditScreen)
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}