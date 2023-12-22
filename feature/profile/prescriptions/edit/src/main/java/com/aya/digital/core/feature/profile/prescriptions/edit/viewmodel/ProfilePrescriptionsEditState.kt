package com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfilePrescriptionsEditState(
    val organisationId: Int? = null,
    val organisationName: String? = null,
    val number: String? = null,
    val numberError: String? = null,
    val photo: Int? = null,
    val photoLink: String? = null,
    val id:Int? = null
) : BaseState
