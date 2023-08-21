package com.aya.digital.core.feature.insurance.list.ui.model

import android.content.Context
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceDoctorState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePolicyUIModel

class ProfileInsuranceDoctorStateTransformer(context: Context) :
    BaseStateTransformer<ProfileInsuranceDoctorState, ProfileInsuranceDoctorUiModel>() {
    override fun invoke(state: ProfileInsuranceDoctorState): ProfileInsuranceDoctorUiModel =
        ProfileInsuranceDoctorUiModel(
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