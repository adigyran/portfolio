package com.aya.digital.core.ui.delegates.fields.validated.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.fields.validated.model.ValidatedFieldUIModel
import com.aya.digital.core.ui.delegates.fields.validated.databinding.ItemFieldBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun validatedFieldDelegate(delegateListeners: ValidatedFieldDelegateListeners) = adapterDelegateViewBinding<ValidatedFieldUIModel,DiffItem,ItemFieldBinding>(
    { layoutInflater, root -> ItemFieldBinding.inflate(layoutInflater, root, false) }
) {

    bind {

    }
}

class ValidatedFieldDelegateListeners(val signInClick: () -> Unit)
