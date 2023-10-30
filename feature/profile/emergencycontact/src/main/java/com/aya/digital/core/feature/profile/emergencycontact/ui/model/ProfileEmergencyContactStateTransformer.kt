package com.aya.digital.core.feature.profile.emergencycontact.ui.model

import android.content.Context
import android.text.InputType
import com.aya.digital.core.feature.profile.emergencycontact.FieldsTags
import com.aya.digital.core.feature.profile.emergencycontact.viewmodel.ProfileEmergencyContactState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.masks.CommonMasks
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.PhoneFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.validated.model.ValidatedFieldUIModel
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.EmergencyContactInfoUIModel
import ru.tinkoff.decoro.MaskImpl
import timber.log.Timber


class ProfileEmergencyContactStateTransformer(context: Context) :
    BaseStateTransformer<ProfileEmergencyContactState, ProfileEmergencyContactUiModel>() {
    override fun invoke(state: ProfileEmergencyContactState): ProfileEmergencyContactUiModel =
        ProfileEmergencyContactUiModel(
            data = kotlin.run {
                Timber.d("${state.editableEmergencyContact}")
                if (state.editMode) {
                    return@run mutableListOf<DiffItem>().apply {
                        add(
                            NameFieldUIModel(
                                FieldsTags.NAME_FIELD,
                                "Contact Name",
                                state.editableEmergencyContact?.contactNameEditable.getEditableField(),
                                state.editableEmergencyContact?.contactNameError
                            )
                        )
                        add(
                            ValidatedFieldUIModel(
                                tag = FieldsTags.PHONE_FIELD,
                                label = "Contact Phone",
                                state.editableEmergencyContact?.contactPhoneEditable.getEditableField(),
                                state.editableEmergencyContact?.contactPhoneError,
                                inputType = InputType.TYPE_CLASS_NUMBER,
                                mask = CommonMasks.getUsPhoneValidator()
                            )

                        )
                        add(
                            NameFieldUIModel(
                                tag = FieldsTags.SUMMARY_FIELD,
                                label = "Contact Summary",
                                state.editableEmergencyContact?.contactSummaryEditable.getEditableField(),
                                state.editableEmergencyContact?.contactSummaryError
                            )

                        )

                        add(
                            SelectionFieldUIModel(
                                tag = FieldsTags.TYPE_FIELD,
                                label = "Contact Type",
                                state.editableEmergencyContact?.contactTypeEditable.getEditableField(),
                                state.editableEmergencyContact?.contactTypeError
                            )

                        )

                    }
                } else {
                    return@run mutableListOf<DiffItem>().apply {
                        state.emergencyContacts?.run {
                            addAll(state.emergencyContacts.map {
                                EmergencyContactInfoUIModel(
                                    id = it.Id,
                                    fullName = it.name?:"",
                                    phone = it.phone?:"",
                                    summary = it.summary?:"",
                                    type = it.type?.name?:""
                                )
                            })

                        }

                    }
                }
            },
            buttonText = kotlin.run {
                if (state.editMode) "Save" else "Create"
            }
        )



    fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()
    private fun String?.getEditableField() = this ?: ""


    private fun String?.getMaskedText(mask: MaskImpl) = this?.let {
        mask.insertFront(it)
        mask.toString()
    }

}