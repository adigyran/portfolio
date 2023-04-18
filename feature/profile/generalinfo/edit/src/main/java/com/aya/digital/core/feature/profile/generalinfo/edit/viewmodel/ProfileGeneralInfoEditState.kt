package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import android.net.Uri
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseState
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProfileGeneralInfoEditState(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: @RawValue LocalDate? = null,
    val sex: ProfileSex? = null,
    val height: String? = null,
    val heightError: String? = null,
    val weight: String? = null,
    val weightError: String? = null,
    val shortAddress: String? = null,
    val ssnOrTin: String? = null,
    val ssnOrTinError: String? = null,
    val avatarUri:Uri? = null,
    val avatarUrl:String = ""
) : BaseState
