package com.aya.digital.core.ui.delegates.profile.insurance.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.address.databinding.ItemAutocompleteAddressSuggestionBinding
import com.aya.digital.core.ui.delegates.profile.insurance.model.AutocompleteAddressSuggestionUIModel

class AutocompleteAddressSuggestionDelegate(private val onSuggestionClick: (id: String) -> Unit) :
    BaseDelegate<AutocompleteAddressSuggestionUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is AutocompleteAddressSuggestionUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<AutocompleteAddressSuggestionUIModel> {
        val binding =
            ItemAutocompleteAddressSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemAutocompleteAddressSuggestionBinding) :
        BaseViewHolder<AutocompleteAddressSuggestionUIModel>(binding.root) {

        init {
            binding.root bindClick { onSuggestionClick(item.id) }
        }
        override fun bind(item: AutocompleteAddressSuggestionUIModel) {
            super.bind(item)
            binding.primary.text = item.primaryText
            binding.secondary.text = item.secondaryText

        }
    }
}