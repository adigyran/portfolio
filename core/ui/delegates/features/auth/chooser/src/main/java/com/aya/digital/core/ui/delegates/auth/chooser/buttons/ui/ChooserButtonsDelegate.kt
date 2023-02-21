package com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui

import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.model.ChooserButtonsUIModel
import com.aya.digital.core.ui.delegates.features.auth.chooser.databinding.ItemChooserButtonsBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun chooserButtonsDelegate(delegateListeners: ButtonsDelegateListeners) = adapterDelegateViewBinding<ChooserButtonsUIModel,DiffItem, ItemChooserButtonsBinding>(
    { layoutInflater, root -> ItemChooserButtonsBinding.inflate(layoutInflater, root, false) }
) {

    binding.btnSignin bindClick {delegateListeners.signInClick()}
    binding.btnSignup bindClick {delegateListeners.signUpClick()}
    bind {

    }
}

class ButtonsDelegateListeners(val signInClick: () -> Unit, val signUpClick: () -> Unit)
