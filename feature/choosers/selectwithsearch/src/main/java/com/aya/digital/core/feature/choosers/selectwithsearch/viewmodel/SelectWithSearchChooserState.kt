package com.aya.digital.core.feature.choosers.multiselect.viewmodel

import com.aya.digital.core.feature.choosers.multiselect.model.SelectionItem
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectWithSearchChooserState(
    val searchTerm: String? = null,
    val selectedItems: Set<SelectionItem> = setOf(),
    val items: List<SelectionItem> = listOf()
) : BaseState

