package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileGeneralInfoEditState(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: LocalDate? = null,
    val sex: ProfileSex? = null,
    val height: String? = null,
    val heightError: String? = null,
    val weight: String? = null,
    val weightError: String? = null,
    val shortAddress: String? = null,
    val ssnOrTin: String? = null,
    val ssnOrTinError: String? = null
) : BaseState
