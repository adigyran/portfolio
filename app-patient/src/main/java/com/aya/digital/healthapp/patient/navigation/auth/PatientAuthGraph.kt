package com.aya.digital.healthapp.patient.navigation.auth

import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordNavigationEvents
import com.aya.digital.core.feature.auth.signin.navigation.SignInNavigationEvents
import com.aya.digital.core.feature.auth.signin.navigation.SignInScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.auth.chooser.navigation.AuthChooserNavigationEvents
import com.aya.digital.feature.auth.chooser.navigation.AuthChooserScreen
import com.aya.digital.feature.auth.container.navigation.AuthContainerNavigationEvents
import com.aya.digital.feature.auth.signup.navigation.SignUpNavigationEvents
import com.aya.digital.feature.auth.signup.navigation.SignUpScreen
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.github.terrakok.cicerone.Router

class PatientAuthGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        fun signIn() = navigationRouter.navigateTo(SignInScreen)
        fun signUp() = navigationRouter.navigateTo(SignUpScreen)
        when (event) {
            AuthContainerNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(AuthChooserScreen)
            }

            AuthChooserNavigationEvents.SignUp -> {
                signUp()
            }

            AuthChooserNavigationEvents.SignIn -> {
                signIn()
            }
            is SignUpNavigationEvents.SelectInsuranceCompanies -> {
                parentCoordinatorRouter.sendEvent(
                    RootContainerNavigationEvents.SelectMultipleItems(
                        event.requestCode,
                        event.selectedItems
                    )
                )
            }

            is SignUpNavigationEvents.EnterCode -> {
                parentCoordinatorRouter.sendEvent(
                    RootContainerNavigationEvents.EnterCode(
                        event.requestCode,
                        event.email
                    )
                )
            }


            SignUpNavigationEvents.SignIn -> {
                navigationRouter.exit()
                signIn()
            }


            SignInNavigationEvents.SignedIn -> {
                navigationRouter.exit()
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenBottomNavigationScreenDefault)
            }

            SignInNavigationEvents.SignUp -> {
                navigationRouter.exit()
                signUp()
            }

            is SignInNavigationEvents.RestorePassword -> {
                parentCoordinatorRouter.sendEvent(
                    RootContainerNavigationEvents.RestorePassword(event.requestCode)
                )
            }

            is RestorePasswordNavigationEvents.EnterCode -> {
                parentCoordinatorRouter.sendEvent(
                    RootContainerNavigationEvents.EnterCode(
                        event.requestCode,
                        event.email
                    )
                )
            }

            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}