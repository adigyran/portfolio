package com.aya.digital.core.feature.tabviews.profile.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProfileState(
    val firstName: String? = null,
    val lastName: String? = null,
    val avatar: String? = null,
    val dateOFBirth: @RawValue LocalDate? = null
) : BaseState
