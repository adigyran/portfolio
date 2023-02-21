package com.aya.digital.core.feature.auth.signin.viewmodel

import com.aya.digital.core.feature.auth.signin.navigation.SignInNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

class SignInViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<SignInState, BaseSideEffect>() {
    override val container = container<SignInState, BaseSideEffect>(
        initialState = SignInState(),
    )
    {

    }

    fun onSignInClicked() = intent {
    }

    fun onSignUpClicked() = intent {
        coordinatorRouter.sendEvent(SignInNavigationEvents.SignUp)
    }
}

