package com.aya.digital.core.feature.prescriptions.list.ui.model

import android.content.Context
import com.aya.digital.core.feature.prescriptions.list.viewmodel.ProfilePrescriptionsListState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePolicyUIModel

class ProfilePrescriptionsListStateTransformer(context: Context) :
    BaseStateTransformer<ProfilePrescriptionsListState, ProfilePrescriptionsListUiModel>() {
    override fun invoke(state: ProfilePrescriptionsListState): ProfilePrescriptionsListUiModel =
        ProfilePrescriptionsListUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}