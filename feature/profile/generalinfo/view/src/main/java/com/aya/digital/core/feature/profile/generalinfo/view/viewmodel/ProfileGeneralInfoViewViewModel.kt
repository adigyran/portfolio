package com.aya.digital.core.feature.profile.generalinfo.view.viewmodel

import com.aya.digital.core.feature.profile.generalinfo.view.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileGeneralInfoViewViewModel(
    private val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<ProfileGeneralInfoViewState, BaseSideEffect>() {
    override val container = container<ProfileGeneralInfoViewState, BaseSideEffect>(
        initialState = ProfileGeneralInfoViewState(),
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

