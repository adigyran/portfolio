package com.aya.digital.core.feature.profile.prescriptions.view.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileInsuranceAddState(
    val organisationId: Int? = null,
    val organisationName: String? = null,
    val number: String? = null,
    val numberError: String? = null,
    val photo: Int? = null,
    val photoLink: String? = null,
    val id:Int? = null
) : BaseState
