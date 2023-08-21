package com.aya.digital.core.feature.insurance.list.viewmodel

import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileInsuranceDoctorState(
    val insuranceModels:List<InsurancePolicyItemModel>? = null
) : BaseState
