package com.aya.digital.feature.auth.signup.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.auth.signup.navigation.SignUpNavigationEvents
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

class SignUpViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<SignUpState, BaseSideEffect>() {
    override val container = container<SignUpState, BaseSideEffect>(
        initialState = SignUpState(),
    )
    {

    }

    fun onSignInClicked() = intent {
    }

    fun onSignUpClicked() = intent {
        coordinatorRouter.sendEvent(SignUpNavigationEvents.SignUp)
    }
}

