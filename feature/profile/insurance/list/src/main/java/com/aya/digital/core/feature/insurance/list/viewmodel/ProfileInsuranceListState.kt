package com.aya.digital.core.feature.insurance.list.viewmodel

import com.aya.digital.core.domain.profile.insurance.model.InsuranceModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileInsuranceListState(
    val insuranceModels:List<InsuranceModel>? = null
) : BaseState
