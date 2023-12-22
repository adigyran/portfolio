package com.aya.digital.core.feature.profile.prescriptions.view.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.prescriptions.view.FieldsTags
import com.aya.digital.core.feature.profile.prescriptions.view.viewmodel.ProfilePrescriptionsViewState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePolicyPhotoUIModel


class ProfilePrescriptionsViewStateTransformer(private val context: Context) :
    BaseStateTransformer<ProfilePrescriptionsViewState, ProfilePrescriptionsViewUiModel>() {
    override fun invoke(state: ProfilePrescriptionsViewState): ProfilePrescriptionsViewUiModel =
        ProfilePrescriptionsViewUiModel(

        )


}