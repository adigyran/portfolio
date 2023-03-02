package com.aya.digital.core.ui.delegates.components.buttons.filledbutton.ui

import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.buttons.filledbutton.databinding.ItemFilledButtonBinding
import com.aya.digital.core.ui.delegates.components.buttons.filledbutton.model.FilledButtonUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun filledButtonDelegate(delegateListeners: FilledButtonDelegateListeners) = adapterDelegateViewBinding<FilledButtonUIModel,DiffItem, ItemFilledButtonBinding>(
    { layoutInflater, root -> ItemFilledButtonBinding.inflate(layoutInflater, root, false) }
) {

    binding.btn bindClick {delegateListeners.onClick()}
    bind {

    }
}

class FilledButtonDelegateListeners(val onClick: () -> Unit)
