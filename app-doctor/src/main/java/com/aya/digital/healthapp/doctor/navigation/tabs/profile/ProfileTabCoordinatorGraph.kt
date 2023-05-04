package com.aya.digital.healthapp.doctor.navigation.tabs.profile

import com.aya.digital.core.feature.profile.security.changeemailphone.navigation.ProfileSecurityChangeEmailPhoneNavigationEvents
import com.aya.digital.core.feature.profile.security.changeemailphone.navigation.ProfileSecurityChangeEmailPhoneScreen
import com.aya.digital.core.feature.profile.security.changepassword.navigation.ProfileSecurityChangePasswordNavigationEvents
import com.aya.digital.core.feature.profile.security.changepassword.navigation.ProfileSecurityChangePasswordScreen
import com.aya.digital.core.feature.profile.security.securitysummary.navigation.ProfileSecuritySummaryNavigationEvents
import com.aya.digital.core.feature.profile.security.securitysummary.navigation.ProfileSecuritySummaryScreen
import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileNavigationEvents
import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.tabs.profile.navigation.ProfileTabNavigationEvents
import com.github.terrakok.cicerone.Router

class ProfileTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        val mainRouter = navigationRouter
        when (event) {
            ProfileTabNavigationEvents.OpenDefaultScreen -> {
                mainRouter.newRootScreen(ProfileScreen)
            }
            ProfileNavigationEvents.OpenProfileGeneralInfo -> {
            }
            ProfileNavigationEvents.OpenProfileEmergencyContact -> {
            }
            ProfileNavigationEvents.OpenProfileInsurance -> {
            }
            ProfileNavigationEvents.OpenProfileSecurity -> {
                mainRouter.navigateTo(ProfileSecuritySummaryScreen)
            }
            ProfileNavigationEvents.OpenProfileNotification -> {
            }

            is ProfileSecuritySummaryNavigationEvents.ChangeEmail -> {
                mainRouter.navigateTo(ProfileSecurityChangeEmailPhoneScreen(event.requestCode))
            }

            is ProfileSecuritySummaryNavigationEvents.ChangePassword -> {
                mainRouter.navigateTo(ProfileSecurityChangePasswordScreen(event.requestCode))
            }

            is ProfileSecurityChangePasswordNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode,event.result)
                mainRouter.exit()
            }

            is ProfileSecurityChangeEmailPhoneNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode,event.result)
                mainRouter.exit()
            }
            CoordinatorEvent.Back ->
            {
                navigationRouter.exit()
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }

    }


}