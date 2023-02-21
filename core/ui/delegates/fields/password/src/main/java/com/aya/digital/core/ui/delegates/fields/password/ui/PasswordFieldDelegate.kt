package com.aya.digital.core.ui.delegates.fields.emailphone.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.fields.emailphone.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.fields.password.databinding.ItemFieldBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun passwordFieldDelegate(delegateListeners: PasswordFieldDelegateListeners) = adapterDelegateViewBinding<PasswordFieldUIModel,DiffItem,ItemFieldBinding>(
    { layoutInflater, root -> ItemFieldBinding.inflate(layoutInflater, root, false) }
) {

    bind {

    }
}

class PasswordFieldDelegateListeners(val signInClick: () -> Unit)
