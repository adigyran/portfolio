package com.aya.digital.core.ui.delegates.fields.name.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.fields.name.databinding.ItemFieldBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun nameFieldDelegate(delegateListeners: NameFieldDelegateListeners) = adapterDelegateViewBinding<NameFieldUIModel,DiffItem,ItemFieldBinding>(
    { layoutInflater, root -> ItemFieldBinding.inflate(layoutInflater, root, false) }
) {

    bind {

    }
}

class NameFieldDelegateListeners(val signInClick: () -> Unit)
