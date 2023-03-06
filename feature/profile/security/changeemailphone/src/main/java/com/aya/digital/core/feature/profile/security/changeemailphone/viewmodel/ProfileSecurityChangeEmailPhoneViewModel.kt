package com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel

import com.aya.digital.core.feature.profile.security.changeemailphone.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileSecurityChangeEmailPhoneViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfileSecurityChangeEmailPhoneState, BaseSideEffect>() {
    override val container = container<ProfileSecurityChangeEmailPhoneState, BaseSideEffect>(
        initialState = ProfileSecurityChangeEmailPhoneState(),
    )
    {

    }

    fun emailChanged(email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

    fun passwordChanged(tag: Int, password: String) = intent {
        if (tag == FieldsTags.PASSWORD_FIELD_TAG) {
            if (state.password != password) reduce { state.copy(password = password) }
        }
    }

    fun onSignInClicked() = intent {

    }

    fun onSignUpClicked() = intent {
    }

}

