package com.aya.digital.core.feature.tabviews.home.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class HomeUiModel(val data: List<DiffItem>? = null) : BaseUiModel
