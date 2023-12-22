package com.aya.digital.core.feature.prescriptions.list.viewmodel

import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfilePrescriptionsListState(
    val insuranceModels:List<InsurancePolicyItemModel>? = null
) : BaseState
