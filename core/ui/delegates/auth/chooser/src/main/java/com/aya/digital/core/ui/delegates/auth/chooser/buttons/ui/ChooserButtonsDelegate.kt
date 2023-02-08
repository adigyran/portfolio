package com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.model.ChooserButtonsUIModel
import com.aya.digital.core.ui.delegates.auth.chooser.databinding.ItemChooserButtonsBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun chooserButtonsDelegate() = adapterDelegateViewBinding<ChooserButtonsUIModel,DiffItem,ItemChooserButtonsBinding>(
    { layoutInflater, root -> ItemChooserButtonsBinding.inflate(layoutInflater, root, false) }
) {

    bind {

    }
}
