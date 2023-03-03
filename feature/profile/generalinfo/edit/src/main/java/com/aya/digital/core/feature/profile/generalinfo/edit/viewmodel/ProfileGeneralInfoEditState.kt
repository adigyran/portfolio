package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileGeneralInfoEditState(
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String = "",
    val birthDate : LocalDate? = null,
    val sex:ProfileSex? = null,
    val height:String? = null,
    val weight:String? = null,
    val shortAddress:String? = null
) : BaseState
