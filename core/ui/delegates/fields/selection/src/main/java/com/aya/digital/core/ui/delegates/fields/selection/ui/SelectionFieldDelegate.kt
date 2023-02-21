package com.aya.digital.core.ui.delegates.fields.selection.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.fields.selection.model.SelectionFieldUIModel
import com.aya.digital.core.ui.delegates.fields.selection.databinding.ItemFieldBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun selectionFieldDelegate(delegateListeners: SelectionFieldDelegateListeners) = adapterDelegateViewBinding<SelectionFieldUIModel,DiffItem, ItemFieldBinding>(
    { layoutInflater, root -> ItemFieldBinding.inflate(layoutInflater, root, false) }
) {

    bind {

    }
}

class SelectionFieldDelegateListeners(val signInClick: () -> Unit)
