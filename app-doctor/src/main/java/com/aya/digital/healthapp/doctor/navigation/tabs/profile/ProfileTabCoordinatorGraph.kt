package com.aya.digital.healthapp.doctor.navigation.tabs.profile

import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceDoctorNavigationEvents
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceDoctorScreen
import com.aya.digital.core.feature.profile.clinicinfo.navigation.ProfileClinicInfoScreen
import com.aya.digital.core.feature.profile.generalinfo.edit.navigation.ProfileGeneralInfoEditNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.edit.navigation.ProfileGeneralInfoEditScreen
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewScreen
import com.aya.digital.core.feature.profile.notifications.navigation.ProfileNotificationsScreen
import com.aya.digital.core.feature.profile.security.changeemail.navigation.ProfileSecurityChangeEmailNavigationEvents
import com.aya.digital.core.feature.profile.security.changeemail.navigation.ProfileSecurityChangeEmailScreen
import com.aya.digital.core.feature.profile.security.changepassword.navigation.ProfileSecurityChangePasswordNavigationEvents
import com.aya.digital.core.feature.profile.security.changepassword.navigation.ProfileSecurityChangePasswordScreen
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

            ProfileNavigationEvents.OpenClinicInfo -> {
                mainRouter.navigateTo(ProfileClinicInfoScreen)
            }

            ProfileNavigationEvents.OpenProfileInsurance -> {
                mainRouter.navigateTo(ProfileInsuranceDoctorScreen)
            }

            ProfileNavigationEvents.OpenProfileSecurity -> {
                mainRouter.navigateTo(ProfileSecuritySummaryScreen)
            }

            ProfileNavigationEvents.OpenProfileNotification -> {
                mainRouter.navigateTo(ProfileNotificationsScreen)
            }

            is ProfileGeneralInfoViewNavigationEvents.EditProfile -> {
                mainRouter.navigateTo(
                    ProfileGeneralInfoEditScreen(
                        event.requestCode
                    )
                )
            }

            is ProfileSecuritySummaryNavigationEvents.ChangeEmail -> {
                mainRouter.navigateTo(ProfileSecurityChangeEmailScreen(event.requestCode))
            }

            is ProfileSecuritySummaryNavigationEvents.ChangePassword -> {
                mainRouter.navigateTo(ProfileSecurityChangePasswordScreen(event.requestCode))
            }

            is ProfileSecurityChangePasswordNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode, event.result)
                mainRouter.exit()
            }

            is ProfileSecurityChangeEmailNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode, event.result)
                mainRouter.exit()
            }

            is ProfileInsuranceDoctorNavigationEvents.SelectInsuranceCompanies -> {
                parentCoordinatorRouter.sendEvent(
                    RootContainerNavigationEvents.SelectMultipleItems(
                        event.requestCode,
                        event.organisationIds?.toSet() ?: emptySet()
                    )
                )
            }

            is ProfileGeneralInfoEditNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode, event.result)
                mainRouter.exit()
            }

            is ProfileGeneralInfoViewNavigationEvents.FinishWithResult -> {
                mainRouter.sendResult(event.requestCode, Unit)
                mainRouter.exit()
            }

            CoordinatorEvent.Back -> {
                navigationRouter.exit()
            }

            else -> parentCoordinatorRouter.sendEvent(event)
        }

    }


}