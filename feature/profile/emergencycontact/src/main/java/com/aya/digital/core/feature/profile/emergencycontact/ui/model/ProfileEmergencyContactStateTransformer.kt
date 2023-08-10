package com.aya.digital.core.feature.profile.emergencycontact.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.emergencycontact.FieldsTags
import com.aya.digital.core.feature.profile.emergencycontact.viewmodel.ProfileEmergencyContactState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.masks.CommonMasks
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.PhoneFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.EmergencyContactInfoUIModel
import ru.tinkoff.decoro.MaskImpl


class ProfileEmergencyContactStateTransformer(context: Context) :
    BaseStateTransformer<ProfileEmergencyContactState, ProfileEmergencyContactUiModel>() {
    override fun invoke(state: ProfileEmergencyContactState): ProfileEmergencyContactUiModel =
        ProfileEmergencyContactUiModel(
            data = kotlin.run {
                if (state.editMode) {
                    return@run mutableListOf<DiffItem>().apply {
                        add(
                            NameFieldUIModel(
                                FieldsTags.NAME_FIELD,
                                "Contact Name",
                                state.contactName,
                                state.contactNameError
                            )
                        )
                        add(
                            PhoneFieldUIModel(
                                tag = FieldsTags.PHONE_FIELD,
                                label = "Contact Phone",
                                state.contactPhone.getField(),
                                state.contactPhoneError,
                                CommonMasks.getUsPhoneValidator()
                            )
                        )

                    }
                } else {
                    return@run mutableListOf<DiffItem>().apply {
                        add(
                            EmergencyContactInfoUIModel(
                                "Contact Name",
                                state.contactName.getField()
                            )
                        )
                        add(
                            EmergencyContactInfoUIModel(
                                "Contact Phone",
                                state.contactPhone.getMaskedText(CommonMasks.getUsPhoneValidator()).getField()
                            )
                        )
                    }
                }
            },
            buttonText = kotlin.run {
                if (state.editMode) "Save" else "Edit"
            }
        )

    private fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()

    private fun String?.getMaskedText(mask: MaskImpl) = this?.let {
        mask.insertFront(it)
        mask.toString()
    }

}