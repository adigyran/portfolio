package com.aya.digital.core.feature.profile.security.changepassword.viewmodel

import com.aya.digital.core.feature.profile.security.changepassword.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileSecurityChangePasswordViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfileSecurityChangePasswordState, BaseSideEffect>() {
    override val container = container<ProfileSecurityChangePasswordState, BaseSideEffect>(
        initialState = ProfileSecurityChangePasswordState(),
    )
    {

    }
    fun passwordChanged(tag: Int, password: String) = intent {
        if (tag == FieldsTags.NEW_REPEAT_PASSWORD_FIELD_TAG) {
            if (state.password != password) reduce { state.copy(password = password) }
        }
    }

}

