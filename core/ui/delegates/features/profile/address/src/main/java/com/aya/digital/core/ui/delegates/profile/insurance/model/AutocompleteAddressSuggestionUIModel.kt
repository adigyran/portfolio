package com.aya.digital.core.ui.delegates.profile.insurance.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class AutocompleteAddressSuggestionUIModel(
    val id: String, val primaryText: String, val secondaryText: String
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is AutocompleteAddressSuggestionUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is AutocompleteAddressSuggestionUIModel
                && newItem.id == this.id
                && newItem.primaryText == this.primaryText
                && newItem.secondaryText == this.secondaryText
}