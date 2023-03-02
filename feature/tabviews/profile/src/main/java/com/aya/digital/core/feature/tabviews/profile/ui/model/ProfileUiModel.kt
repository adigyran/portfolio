package com.aya.digital.core.feature.tabviews.profile.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileUiModel(val data: List<DiffItem>? = null) : BaseUiModel
