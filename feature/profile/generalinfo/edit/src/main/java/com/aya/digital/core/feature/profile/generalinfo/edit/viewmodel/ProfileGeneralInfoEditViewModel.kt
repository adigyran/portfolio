package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.feature.profile.generalinfo.edit.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileGeneralInfoEditViewModel(
    private val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<ProfileGeneralInfoEditState, BaseSideEffect>() {
    override val container = container<ProfileGeneralInfoEditState, BaseSideEffect>(
        initialState = ProfileGeneralInfoEditState(),
    )
    {

    }

    fun onSaveProfileClicked() = intent {

    }


}

