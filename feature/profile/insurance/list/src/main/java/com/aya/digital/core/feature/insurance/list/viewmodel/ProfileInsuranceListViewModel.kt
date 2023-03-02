package com.aya.digital.core.feature.insurance.list.viewmodel

import com.aya.digital.core.feature.insurance.list.FieldsTags
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileInsuranceListViewModel(
    private val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<ProfileInsuranceListState, BaseSideEffect>() {
    override val container = container<ProfileInsuranceListState, BaseSideEffect>(
        initialState = ProfileInsuranceListState(),
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

