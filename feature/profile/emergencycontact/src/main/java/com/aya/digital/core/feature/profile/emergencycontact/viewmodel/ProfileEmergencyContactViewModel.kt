package com.aya.digital.core.feature.profile.emergencycontact.viewmodel

import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.SaveEmergencyContactUseCase
import com.aya.digital.core.feature.profile.emergencycontact.FieldsTags
import com.aya.digital.core.feature.profile.emergencycontact.navigation.ProfileEmergencyContactNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
    override val container =
        container<ProfileEmergencyContactState, ProfileEmergencyContactSideEffects>(
            initialState = ProfileEmergencyContactState(),
        )
        {

        }

    init {
        getEmergencyContact()
    }


    fun onNameFieldChanged(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.NAME_FIELD -> reduce { state.copy(contactNameEditable = text) }
        }
    }

    fun onPhoneFieldChanged(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.PHONE_FIELD -> reduce {
                state.copy(contactPhoneEditable = text)
            }
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
        if (state.contactNameEditable.isNullOrBlank() || state.contactPhoneEditable.isNullOrBlank()) return@intent
        val await = saveEmergencyContactUseCase(state.contactNameEditable!!, state.contactPhoneEditable!!).await()
        await.processResult({
            getEmergencyContact()
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
        reduce {
            state.copy(
                editMode = true,
                contactNameEditable = null,
                contactPhoneEditable = null
            )
        }

        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                delay(10L) // non-blocking delay for 1 second (default time unit is ms)
                reduce {
                    state.copy(
                        contactNameEditable = state.contactName,
                        contactPhoneEditable = state.contactPhone
                    )
                }
            }
        }

    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileEmergencyContactSideEffects.Error(errorSideEffect))
    }

    override fun onBack() = intent {
        if (state.editMode) reduce { state.copy(editMode = false) }
        else coordinatorRouter.sendEvent(CoordinatorEvent.Back)

    }
}

