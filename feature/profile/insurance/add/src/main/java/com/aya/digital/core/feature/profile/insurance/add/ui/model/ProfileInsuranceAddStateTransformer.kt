package com.aya.digital.core.feature.profile.insurance.add.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.insurance.add.viewmodel.ProfileInsuranceAddState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem


class ProfileInsuranceAddStateTransformer(context : Context): BaseStateTransformer<ProfileInsuranceAddState, ProfileInsuranceAddUiModel>() {
    override fun invoke(state: ProfileInsuranceAddState): ProfileInsuranceAddUiModel =
        ProfileInsuranceAddUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}