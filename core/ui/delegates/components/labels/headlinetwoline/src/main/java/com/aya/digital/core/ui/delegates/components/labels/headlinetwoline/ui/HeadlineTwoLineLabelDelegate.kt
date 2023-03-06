package com.aya.digital.core.ui.delegates.components.labels.headline.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineTwoLineLabelUIModel
import com.aya.digital.core.ui.delegates.components.labels.headlinetwoline.databinding.ItemHeadlineTwolineLabelBinding

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun headlineTwoLineLabelDelegate() =
    adapterDelegateViewBinding<HeadlineTwoLineLabelUIModel, DiffItem, ItemHeadlineTwolineLabelBinding>(
        { layoutInflater, root -> ItemHeadlineTwolineLabelBinding.inflate(layoutInflater, root, false) }
    ) {

        var initialised = false
        bind {
            if(!initialised)
            {
                binding.topTextTv.text = item.topText
                binding.bottomTextTv.text = item.bottomText
                initialised = true
            }
        }
    }

