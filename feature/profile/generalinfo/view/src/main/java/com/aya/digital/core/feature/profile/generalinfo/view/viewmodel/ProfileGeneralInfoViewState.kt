package com.aya.digital.core.feature.profile.generalinfo.view.viewmodel

import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileGeneralInfoViewState(
    val avatar: String = "",
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: LocalDate? = null,
    val ssn: String? = null,
    val tin: String? = null,
    val sex: ProfileSex? = null,
    val height: String? = null,
    val weight: String? = null,
    val shortAddress: String? = null
) : BaseState
