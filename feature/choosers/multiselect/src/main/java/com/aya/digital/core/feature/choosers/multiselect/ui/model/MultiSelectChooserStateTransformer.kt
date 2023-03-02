package com.aya.digital.core.feature.choosers.multiselect.ui.model

import android.content.Context
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.MultiSelectChooserState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.choosers.multiselect.model.MultiSelectItemUIModel

class MultiSelectChooserStateTransformer(context: Context) :
    BaseStateTransformer<MultiSelectChooserState, MultiSelectChooserUiModel>() {
    override fun invoke(state: MultiSelectChooserState): MultiSelectChooserUiModel =
        MultiSelectChooserUiModel(
            data = kotlin.run {
                if (state.items.isEmpty()) return@run listOf<DiffItem>()
                return@run mutableListOf<DiffItem>()
                    .apply { addAll(
                        state.items.map {
                            MultiSelectItemUIModel(
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