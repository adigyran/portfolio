package com.aya.digital.core.feature.profile.generalinfo.edit.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileGeneralInfoEditUiModel(val avatarUrl:String,val data: List<DiffItem>? = null) : BaseUiModel
