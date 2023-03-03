package com.aya.digital.core.feature.profile.generalinfo.view.viewmodel

import com.aya.digital.core.feature.profile.generalinfo.view.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileGeneralInfoViewViewModel(
    private val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<ProfileGeneralInfoViewState, BaseSideEffect>() {
    override val container = container<ProfileGeneralInfoViewState, BaseSideEffect>(
        initialState = ProfileGeneralInfoViewState(),
    )
    {

    }


    fun onEditClicked() = intent {

    }

}

