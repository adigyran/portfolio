package com.aya.digital.core.feature.profile.insurance.add.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.insurance.add.FieldsTags
import com.aya.digital.core.feature.profile.insurance.add.viewmodel.ProfileInsuranceAddState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyPhotoUIModel


class ProfileInsuranceAddStateTransformer(private val context : Context): BaseStateTransformer<ProfileInsuranceAddState, ProfileInsuranceAddUiModel>() {
    override fun invoke(state: ProfileInsuranceAddState): ProfileInsuranceAddUiModel =
        ProfileInsuranceAddUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {
                   add(InsurancePolicyPhotoUIModel(state.photo))
                   add(NameFieldUIModel(FieldsTags.NAME_FIELD_TAG,"Name Insurance Company",state.name,null))
                   add(NameFieldUIModel(FieldsTags.NUMBER_FIELD_TAG,"Insurance Number",state.number,state.numberError))
               }
            }
        )


}