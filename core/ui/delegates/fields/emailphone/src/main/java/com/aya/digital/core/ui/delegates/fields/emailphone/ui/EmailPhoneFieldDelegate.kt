package com.aya.digital.core.ui.delegates.fields.emailphone.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.fields.emailphone.databinding.ItemFieldBinding
import com.aya.digital.core.ui.delegates.fields.emailphone.model.EmailPhoneFieldUIModel

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun emailPhoneFieldDelegate(delegateListeners: EmailPhoneDelegateListeners) = adapterDelegateViewBinding<EmailPhoneFieldUIModel,DiffItem, ItemFieldBinding>(
    { layoutInflater, root -> ItemFieldBinding.inflate(layoutInflater, root, false) }
) {

    bind {

    }
}

class EmailPhoneDelegateListeners(val signInClick: () -> Unit)
