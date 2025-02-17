package com.aya.digital.core.ui.delegates.components.buttons.filledbutton.model

import com.aya.digital.core.ui.adapters.base.DiffItem

class FilledButtonUIModel : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is FilledButtonUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is FilledButtonUIModel
}