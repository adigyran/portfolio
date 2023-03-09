package com.aya.digital.core.feature.choosers.multiselect.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class SelectWithSearchChooserUiModel(val searchText :String? = null, val data: List<DiffItem>? = null) : BaseUiModel
