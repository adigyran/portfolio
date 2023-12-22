package com.aya.digital.core.feature.profile.prescriptions.edit.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.prescriptions.edit.FieldsTags
import com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel.ProfilePrescriptionsEditState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePolicyPhotoUIModel


class ProfilePrescriptionsEditStateTransformer(private val context: Context) :
    BaseStateTransformer<ProfilePrescriptionsEditState, ProfilePrescriptionsEditUiModel>() {
    override fun invoke(state: ProfilePrescriptionsEditState): ProfilePrescriptionsEditUiModel =
        ProfilePrescriptionsEditUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(InsurancePolicyPhotoUIModel(state.photoLink))
                    add(
                        SelectionFieldUIModel(
                            FieldsTags.NAME_FIELD_TAG,
                            "Insurance Company Name",
                            state.organisationName,
                            null
                        )
                    )
                    add(
                        NameFieldUIModel(
                            FieldsTags.NUMBER_FIELD_TAG,
                            "Insurance Number",
                            state.number,
                            state.numberError
                        )
                    )
                }
            },
            fullScreenPolicyUrl = state.photoLink,
            saveAddButtonText = kotlin.run { if (state.id != null) "Save" else "Add" }
        )


}