package com.aya.digital.core.feature.choosers.multiselect.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.feature.choosers.multiselect.model.SelectionItem
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectWithSearchChooserState(
    val cursor: String? = null,
    val searchTerm: String? = null,
    val selectedItems: Set<SelectionItem> = setOf(),
    val dataOperation: DataLoadingOperationWithPagination,
    val items: List<SelectionItem> = listOf()
) : BaseState

