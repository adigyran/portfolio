package com.aya.digital.core.feature.profile.emergencycontact.viewmodel

import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.SaveEmergencyContactUseCase
import com.aya.digital.core.feature.profile.emergencycontact.FieldsTags
import com.aya.digital.core.feature.profile.emergencycontact.navigation.ProfileEmergencyContactNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileEmergencyContactViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getEmergencyContactUseCase: GetEmergencyContactUseCase,
    private val saveEmergencyContactUseCase: SaveEmergencyContactUseCase
) :
    BaseViewModel<ProfileEmergencyContactState, ProfileEmergencyContactSideEffects>() {
    override val container = container<ProfileEmergencyContactState, ProfileEmergencyContactSideEffects>(
        initialState = ProfileEmergencyContactState(),
    )
    {

    }

    init {
        getEmergencyContact()
    }


    fun onNameFieldChanged(tag: Int, text: String) = intent {
        when(tag)
        {
          FieldsTags.NAME_FIELD -> reduce { state.copy(contactName = text) }
          FieldsTags.PHONE_FIELD -> reduce { state.copy(contactPhone = text) }
        }
    }

    private fun getEmergencyContact() = intent {
        val await = getEmergencyContactUseCase().await()
        await.processResult({
            reduce {
                state.copy(contactName = it.name, contactPhone = it.phone)
            }
        }, { processError(it) })
    }

    private fun saveEmergencyContact() = intent {
        if (state.contactName.isNullOrBlank() || state.contactPhone.isNullOrBlank()) return@intent
        val await = saveEmergencyContactUseCase(state.contactName!!, state.contactPhone!!).await()
        await.processResult({
            reduce { state.copy(editMode = false) }
        }, { processError(it) })
    }

    fun buttonClicked() = intent {
        if (state.editMode) saveFields() else toggleEdit()
    }

    private fun saveFields() = intent {
        saveEmergencyContact()
    }

    private fun toggleEdit() = intent {
        reduce { state.copy(editMode = true) }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileEmergencyContactSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}

