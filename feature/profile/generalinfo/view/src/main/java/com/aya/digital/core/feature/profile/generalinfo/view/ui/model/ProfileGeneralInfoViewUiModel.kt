package com.aya.digital.core.feature.profile.generalinfo.view.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileGeneralInfoViewUiModel(
    val avatar: String,
    val data: List<DiffItem>? = null
) : BaseUiModel
