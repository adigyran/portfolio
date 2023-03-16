package com.aya.digital.core.feature.insurance.list.ui.model

import android.content.Context
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceListState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyUIModel

class ProfileInsuranceListStateTransformer(context: Context) :
    BaseStateTransformer<ProfileInsuranceListState, ProfileInsuranceListUiModel>() {
    override fun invoke(state: ProfileInsuranceListState): ProfileInsuranceListUiModel =
        ProfileInsuranceListUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.insuranceModels?.run {
                        addAll(state.insuranceModels.map {
                            InsurancePolicyUIModel(
                                id = it.id,
                                name = it.organisationName,
                                number = it.number,
                                photo = it.attachmentUrl,
                                status = null
                            )
                        })
                    }
                }
            }
        )


}