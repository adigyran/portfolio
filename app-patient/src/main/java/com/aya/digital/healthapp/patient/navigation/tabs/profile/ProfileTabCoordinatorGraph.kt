package com.aya.digital.healthapp.patient.navigation.tabs.profile

import com.aya.digital.core.feature.auth.signin.navigation.SignInNavigationEvents
import com.aya.digital.core.feature.auth.signin.navigation.SignInScreen
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.navigation.MultiSelectChooserScreen
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListNavigationEvents
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListScreen
import com.aya.digital.core.feature.profile.emergencycontact.navigation.ProfileEmergencyContactScreen
import com.aya.digital.core.feature.profile.generalinfo.edit.navigation.ProfileGeneralInfoEditScreen
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewScreen
import com.aya.digital.core.feature.profile.insurance.add.navigation.ProfileInsuranceAddNavigationEvents
import com.aya.digital.core.feature.profile.insurance.add.navigation.ProfileInsuranceAddScreen
import com.aya.digital.core.feature.profile.notifications.navigation.ProfileNotificationsScreen
import com.aya.digital.core.feature.profile.security.changeemailphone.navigation.ProfileSecurityChangeEmailPhoneScreen
import com.aya.digital.core.feature.profile.security.changepassword.navigation.ProfileSecurityChangePasswordScreen
import com.aya.digital.core.feature.profile.security.securitysummary.navigation.ProfileSecuritySummaryNavigationEvents
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
        val mainRouter = navigationRouter
        when (event) {
            ProfileTabNavigationEvents.OpenDefaultScreen -> {
                mainRouter.newRootScreen(ProfileScreen)
            }
            ProfileNavigationEvents.OpenProfileGeneralInfo -> {
                mainRouter.navigateTo(ProfileGeneralInfoViewScreen)
            }
            ProfileNavigationEvents.OpenProfileEmergencyContact -> {
                mainRouter.navigateTo(ProfileEmergencyContactScreen)
            }
            ProfileNavigationEvents.OpenProfileInsurance -> {
                mainRouter.navigateTo(ProfileInsuranceListScreen)
            }
            ProfileNavigationEvents.OpenProfileSecurity -> {
                mainRouter.navigateTo(ProfileSecuritySummaryScreen)
            }
            ProfileNavigationEvents.OpenProfileNotification -> {
                mainRouter.navigateTo(ProfileNotificationsScreen)
            }
            ProfileGeneralInfoViewNavigationEvents.EditProfile -> {
                mainRouter.navigateTo(ProfileGeneralInfoEditScreen)
            }

            ProfileSecuritySummaryNavigationEvents.ChangeEmail -> {
                mainRouter.navigateTo(ProfileSecurityChangeEmailPhoneScreen)
            }

            ProfileSecuritySummaryNavigationEvents.ChangePassword -> {
                mainRouter.navigateTo(ProfileSecurityChangePasswordScreen)
            }

            ProfileInsuranceListNavigationEvents.AddInsurance ->
            {
                mainRouter.navigateTo(ProfileInsuranceAddScreen)
            }

            is ProfileInsuranceListNavigationEvents.EditInsurance ->
            {
                mainRouter.navigateTo(ProfileInsuranceAddScreen)
            }

            is ProfileInsuranceAddNavigationEvents.FinishWithResult ->
            {
                mainRouter.sendResult(event.requestCode,event.result)
                mainRouter.exit()
            }

            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}