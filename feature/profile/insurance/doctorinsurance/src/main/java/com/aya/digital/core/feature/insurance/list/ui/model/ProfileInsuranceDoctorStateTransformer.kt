package com.aya.digital.core.feature.insurance.list.ui.model

import android.content.Context
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceDoctorState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePoliciesUIModel
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePolicyUIModel

class ProfileInsuranceDoctorStateTransformer(context: Context) :
    BaseStateTransformer<ProfileInsuranceDoctorState, ProfileInsuranceDoctorUiModel>() {
    override fun invoke(state: ProfileInsuranceDoctorState): ProfileInsuranceDoctorUiModel =
        ProfileInsuranceDoctorUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        InsurancePoliciesUIModel(
                            id = 123,
                            count = state.insurances.getInsurancesCount().getInsurancesCountText()
                        )
                    )
                }
            }
        )

    private fun Set<Int>?.getInsurancesCount() = this?.size ?: 0
    private fun Int.getInsurancesCountText() = "%d insurance".format(this)

}