package com.aya.digital.healthapp.patient.navigation.tabs.profile

import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListNavigationEvents
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListScreen
import com.aya.digital.core.feature.prescriptions.list.navigation.ProfilePrescriptionsListScreen
import com.aya.digital.core.feature.profile.address.navigation.ProfileAddressScreen
import com.aya.digital.core.feature.profile.emergencycontact.navigation.ProfileEmergencyContactNavigationEvents
import com.aya.digital.core.feature.profile.emergencycontact.navigation.ProfileEmergencyContactScreen
import com.aya.digital.core.feature.profile.generalinfo.edit.navigation.ProfileGeneralInfoEditNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.edit.navigation.ProfileGeneralInfoEditScreen
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewScreen
import com.aya.digital.core.feature.profile.insurance.add.navigation.ProfileInsuranceAddNavigationEvents
import com.aya.digital.core.feature.profile.insurance.add.navigation.ProfileInsuranceAddScreen
import com.aya.digital.core.feature.profile.notifications.navigation.ProfileNotificationsScreen
import com.aya.digital.core.feature.profile.security.changeemail.navigation.ProfileSecurityChangeEmailNavigationEvents
import com.aya.digital.core.feature.profile.security.changeemail.navigation.ProfileSecurityChangeEmailScreen
import com.aya.digital.core.feature.profile.security.changepassword.navigation.ProfileSecurityChangePasswordNavigationEvents
import com.aya.digital.core.feature.profile.security.changepassword.navigation.ProfileSecurityChangePasswordScreen
import com.aya.digital.core.feature.profile.security.changephone.navigation.ProfileSecurityChangePhoneNavigationEvents
import com.aya.digital.core.feature.profile.security.changephone.navigation.ProfileSecurityChangePhoneScreen
import com.aya.digital.core.feature.profile.security.securitysummary.navigation.ProfileSecuritySummaryNavigationEvents
import com.aya.digital.core.feature.profile.security.securitysummary.navigation.ProfileSecuritySummaryScreen
import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileNavigationEvents
import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
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
            is ProfileNavigationEvents.OpenProfileGeneralInfo -> {
                mainRouter.navigateTo(ProfileGeneralInfoViewScreen(event.requestCode))
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
            ProfileNavigationEvents.OpenProfileAddress -> {
                mainRouter.navigateTo(ProfileAddressScreen)
            }
            ProfileNavigationEvents.OpenProfileNotification -> {
                mainRouter.navigateTo(ProfileNotificationsScreen)
            }
            ProfileNavigationEvents.OpenProfilePrescriptions -> {
                mainRouter.navigateTo(ProfilePrescriptionsListScreen)
            }
            is ProfileGeneralInfoViewNavigationEvents.EditProfile -> {
                mainRouter.navigateTo(
                    ProfileGeneralInfoEditScreen(
                        event.requestCode
                    )
                )
            }
            is ProfileGeneralInfoEditNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode,event.result)
                mainRouter.exit()
            }

            is ProfileGeneralInfoViewNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode,Unit)
                mainRouter.exit()
            }

            is ProfileSecuritySummaryNavigationEvents.ChangeEmail -> {
                mainRouter.navigateTo(ProfileSecurityChangeEmailScreen(event.requestCode))
            }

            is ProfileSecuritySummaryNavigationEvents.ChangePhone -> {
                mainRouter.navigateTo(ProfileSecurityChangePhoneScreen(event.requestCode,event.phone))
            }

            is ProfileSecuritySummaryNavigationEvents.ChangePassword -> {
                mainRouter.navigateTo(ProfileSecurityChangePasswordScreen(event.requestCode))
            }

            is ProfileSecurityChangePasswordNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode,event.result)
                mainRouter.exit()
            }

            is ProfileSecurityChangeEmailNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode,event.result)
                mainRouter.exit()
            }

            is ProfileSecurityChangePhoneNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode,event.result)
                mainRouter.exit()
            }

            is ProfileInsuranceListNavigationEvents.AddInsurance -> {
                mainRouter.navigateTo(
                    ProfileInsuranceAddScreen(
                        requestCode = event.requestCode,
                        insuranceId = null
                    )
                )
            }

            is ProfileInsuranceListNavigationEvents.EditInsurance -> {
                mainRouter.navigateTo(
                    ProfileInsuranceAddScreen(
                        requestCode = event.requestCode,
                        insuranceId = event.insuranceId
                    )
                )
            }

            is ProfileInsuranceAddNavigationEvents.SelectInsuranceCompany ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectSingleItem(event.requestCode,event.selectedInsurance))
            }

            is ProfileSecurityChangeEmailNavigationEvents.EnterCode ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.EnterCode(event.requestCode,event.email))
            }
            is ProfileSecurityChangePhoneNavigationEvents.EnterCode ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.EnterCode(event.requestCode,event.phone))
            }

            is ProfileEmergencyContactNavigationEvents.SelectContactType ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectSingleItem(event.requestCode,event.selectedContactType))
            }

            is ProfileInsuranceAddNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode, event.result)
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