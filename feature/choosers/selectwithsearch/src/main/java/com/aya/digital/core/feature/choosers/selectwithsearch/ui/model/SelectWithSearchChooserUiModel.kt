package com.aya.digital.core.feature.choosers.selectwithsearch.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class SelectWithSearchChooserUiModel(val title:String,val searchText :String? = null, val data: List<DiffItem>? = null) : BaseUiModel
