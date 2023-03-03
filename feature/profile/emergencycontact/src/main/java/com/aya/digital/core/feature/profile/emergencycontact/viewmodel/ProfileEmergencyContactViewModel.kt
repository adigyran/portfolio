package com.aya.digital.core.feature.profile.emergencycontact.viewmodel

import com.aya.digital.core.feature.profile.emergencycontact.FieldsTags
import com.aya.digital.core.feature.profile.emergencycontact.navigation.ProfileEmergencyContactNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileEmergencyContactViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfileEmergencyContactState, BaseSideEffect>() {
    override val container = container<ProfileEmergencyContactState, BaseSideEffect>(
        initialState = ProfileEmergencyContactState(),
    )
    {

    }

    fun buttonClicked() = intent {
        if(state.editMode) saveFields() else toggleEdit()
    }

    private fun saveFields() = intent {
        reduce { state.copy(editMode = false) }
    }

    private fun toggleEdit() = intent {
        reduce { state.copy(editMode = true) }
    }
}

