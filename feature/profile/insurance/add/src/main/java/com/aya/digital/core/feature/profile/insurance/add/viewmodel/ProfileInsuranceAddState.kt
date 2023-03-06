package com.aya.digital.core.feature.profile.insurance.add.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileInsuranceAddState(
    val name: String? = null,
    val number: String? = null,
    val numberError: String? = null,
    val photo: String? = null
) : BaseState
