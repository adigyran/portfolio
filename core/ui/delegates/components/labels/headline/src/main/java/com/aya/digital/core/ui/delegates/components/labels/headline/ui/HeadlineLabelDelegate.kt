package com.aya.digital.core.ui.delegates.components.labels.headline.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineLabelUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.databinding.ItemHeadlineLabelBinding

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun headlineLabelDelegate(delegateListeners: HeadlineLabelDelegateListeners) =
    adapterDelegateViewBinding<HeadlineLabelUIModel, DiffItem, ItemHeadlineLabelBinding>(
        { layoutInflater, root -> ItemHeadlineLabelBinding.inflate(layoutInflater, root, false) }
    ) {

        bind {
            binding.textField.text = item.labelText
        }
    }

class HeadlineLabelDelegateListeners()
