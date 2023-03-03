package com.aya.digital.core.feature.profile.emergencycontact.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileEmergencyContactState(
    val contactName: String? = null,
    val contactPhone: String? = null,
    val editMode:Boolean = false
) : BaseState
