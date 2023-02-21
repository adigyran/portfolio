package com.aya.digital.feature.auth.chooser.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.auth.chooser.navigation.AuthChooserNavigationEvents
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

class AuthChooserViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<AuthChooserState, BaseSideEffect>() {
    override val container = container<AuthChooserState, BaseSideEffect>(
        initialState = AuthChooserState(),
    )
    {

    }

    fun onSignInClicked() = intent {
        coordinatorRouter.sendEvent(AuthChooserNavigationEvents.SignIn)
    }

    fun onSignUpClicked() = intent {
        coordinatorRouter.sendEvent(AuthChooserNavigationEvents.SignUp)
    }
}

