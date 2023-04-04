package com.aya.digital.core.feature.tabviews.profile.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileState(val firstName:String?=null, val lastName:String?=null, val avatar:String?=null,val dateOFBirth:LocalDate?=null) : BaseState
