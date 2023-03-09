package com.aya.digital.core.feature.choosers.multiselect.ui.model

import android.content.Context
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.SelectWithSearchChooserState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.choosers.selectwithsearch.model.SelectWithSearchItemUIModel

class SelectWithSearchStateTransformer(private val context: Context) :
    BaseStateTransformer<SelectWithSearchChooserState, SelectWithSearchChooserUiModel>() {
    override fun invoke(state: SelectWithSearchChooserState): SelectWithSearchChooserUiModel =
        SelectWithSearchChooserUiModel(
            data = kotlin.run {
                if (state.items.isEmpty()) return@run listOf<DiffItem>()
                return@run mutableListOf<DiffItem>()
                    .apply { addAll(
                        state.items.map {
                            SelectWithSearchItemUIModel(
                                it.id,
                                it.text,
                                state.selectedItems.any { item -> item == it.id }
                            )
                        }
                    ) }
            },
            searchText = state.searchTerm
        )


}