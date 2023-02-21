package com.aya.digital.core.ui.delegates.auth.chooser.buttons.model

import com.aya.digital.core.ui.adapters.base.DiffItem

class ChooserButtonsUIModel : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is ChooserButtonsUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is ChooserButtonsUIModel
}