package com.aya.digital.core.feature.profile.prescriptions.view.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfilePrescriptionsViewState(
    val id:Int? = null
) : BaseState
