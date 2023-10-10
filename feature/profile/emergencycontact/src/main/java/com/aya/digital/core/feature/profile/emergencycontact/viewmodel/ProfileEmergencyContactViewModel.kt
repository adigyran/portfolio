package com.aya.digital.core.feature.profile.emergencycontact.viewmodel

import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.GetEmergencyContactTypeItemByIdUseCase
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactsUseCase
import com.aya.digital.core.domain.profile.emergencycontact.operations.DeleteEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.operations.SaveEmergencyContactUseCase
import com.aya.digital.core.feature.profile.emergencycontact.FieldsTags
import com.aya.digital.core.feature.profile.emergencycontact.navigation.ProfileEmergencyContactNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileEmergencyContactViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getEmergencyContactTypeItemByIdUseCase: GetEmergencyContactTypeItemByIdUseCase,
    private val getEmergencyContactUseCase: GetEmergencyContactUseCase,
    private val getEmergencyContactsUseCase: GetEmergencyContactsUseCase,
    private val deleteEmergencyContactUseCase: DeleteEmergencyContactUseCase,
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
        // getEmergencyContact()
        getEmergencyContacts()
    }


    fun onNameFieldChanged(tag: Int, text: String) = intent {
        if (!state.editMode || state.editableEmergencyContact == null) return@intent
        when (tag) {
            FieldsTags.NAME_FIELD -> reduce {
                state.copy(editableEmergencyContact = state.editableEmergencyContact?.apply {
                    contactNameEditable = text
                })
            }

            FieldsTags.SUMMARY_FIELD -> reduce {
                state.copy(editableEmergencyContact = state.editableEmergencyContact?.apply {
                    contactSummaryEditable = text
                })
            }
        }
    }

    fun onPhoneFieldChanged(tag: Int, text: String) = intent {
        if (!state.editMode || state.editableEmergencyContact == null) return@intent
        when (tag) {
            FieldsTags.PHONE_FIELD -> reduce {
                state.copy(editableEmergencyContact = state.editableEmergencyContact?.apply {
                    contactPhoneEditable = text
                })
            }
        }
    }

    fun onSelectionFieldClicked(tag: Int) = intent {
        if (!state.editMode || state.editableEmergencyContact == null) return@intent
        when (tag) {
            FieldsTags.TYPE_FIELD -> {
                selectContactType()
            }
        }

    }

    private fun selectContactType() = intent {
        if (!state.editMode || state.editableEmergencyContact == null) return@intent
        listenForContactType()
        coordinatorRouter.sendEvent(
            ProfileEmergencyContactNavigationEvents.SelectContactType(
                RequestCodes.EMERGENCY_CONTACT_TYPE_LIST_REQUEST_CODE,
                state.editableEmergencyContact!!.contactTypeEditableId
            )
        )
    }

    private fun listenForContactType() = intent {
        rootCoordinatorRouter.setResultListener(RequestCodes.EMERGENCY_CONTACT_TYPE_LIST_REQUEST_CODE) { result ->
            if (result is MultiSelectResultModel && result.selectedItems.isNotEmpty()) {
                setContactType(result.selectedItems.map { it.id }.first())
            }
        }
    }

    private fun setContactType(contactTypeId: Int) = intent {
        if (!state.editMode || state.editableEmergencyContact == null) return@intent
        getEmergencyContactTypeItemByIdUseCase(contactTypeId)
            .await()
            .processResult({ emergencyContactType ->
                reduce {
                    state.copy(
                        editableEmergencyContact = state.editableEmergencyContact?.apply {
                            this.contactTypeEditableId = contactTypeId
                        }.apply { this?.contactTypeEditable = emergencyContactType.name }
                    )
                }
            }, { processError(it) })
    }

    private fun getEmergencyContacts() = intent {
        val await = getEmergencyContactsUseCase().await()
        await.processResult({ emergencyContactModels ->
            reduce {
                state.copy(
                    editMode = false,
                    emergencyContacts = emergencyContactModels,
                    currentEditableId = null,
                    editableEmergencyContact = null
                )
            }
        }, { processError(it) })
    }


    private fun saveEmergencyContact() = intent {
        if (!state.editMode || state.editableEmergencyContact == null) return@intent
        val currentEditableEC = state.editableEmergencyContact!!
        if (currentEditableEC.contactNameEditable.isNullOrBlank() || currentEditableEC.contactPhoneEditable.isNullOrBlank() || currentEditableEC.contactTypeEditableId == null) return@intent
        saveEmergencyContactUseCase(
            id = state.currentEditableId,
            name = currentEditableEC.contactNameEditable ?: "",
            phone = currentEditableEC.contactPhoneEditable ?: "",
            summary = currentEditableEC.contactSummaryEditable ?: "",
            type = currentEditableEC.contactTypeEditableId ?: -1
        )
            .await()
            .processResult({
                reduce {
                    state.copy(
                        editMode = false,
                        currentEditableId = null,
                        editableEmergencyContact = null
                    )
                }
                getEmergencyContacts()
            }, { processError(it) })

        /*  if (state.contactNameEditable.isNullOrBlank() || state.contactPhoneEditable.isNullOrBlank()) return@intent
          val await = saveEmergencyContactUseCase(state.contactNameEditable!!, state.contactPhoneEditable!!).await()
          await.processResult({
              getEmergencyContact()
              reduce { state.copy(editMode = false) }
          }, { processError(it) })*/
    }

    fun buttonClicked() = intent {
        if (state.editMode) saveFields() else toggleCreate()
    }

    private fun saveFields() = intent {
        saveEmergencyContact()
    }

    fun onEmergencyContactClick(id: Int) = intent {
        toggleEdit(id)
    }

    fun onEmergencyContactMoreClick(id: Int) = intent {
        postSideEffect(ProfileEmergencyContactSideEffects.ShowEmergencyContactActionsDialog(id))
    }

    fun onDeleteEmergencyContact(id: Int) = intent {
        deleteEmergencyContact(id)
    }

    private fun deleteEmergencyContact(id: Int) = intent {
        deleteEmergencyContactUseCase(id)
            .await()
            .processResult({
                           getEmergencyContacts()
            },{processError(it)})
    }

    private fun toggleEdit(id: Int) = intent {
        val selectedEmergencyContact =
            state.emergencyContacts?.firstOrNull { it.Id == id } ?: return@intent
        val editableEmergencyContact = selectedEmergencyContact.run {
            EditableEmergencyContact(
                contactNameEditable = name,
                contactSummaryEditable = summary,
                contactPhoneEditable = phone
            )
        }
        reduce {
            state.copy(
                editMode = true,
                currentEditableId = id,
                editableEmergencyContact = editableEmergencyContact
            )
        }
    }

    private fun toggleCreate() = intent {
        reduce {
            state.copy(
                editMode = true,
                currentEditableId = null,
                editableEmergencyContact = EditableEmergencyContact()
            )
        }
        /*   reduce {
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
           }*/

    }

    private fun editingMode() = intent {

    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileEmergencyContactSideEffects.Error(errorSideEffect))
    }

    override fun onBack() = intent {
        if (state.editMode) reduce { state.copy(editMode = false) }
        else coordinatorRouter.sendEvent(CoordinatorEvent.Back)

    }
}

