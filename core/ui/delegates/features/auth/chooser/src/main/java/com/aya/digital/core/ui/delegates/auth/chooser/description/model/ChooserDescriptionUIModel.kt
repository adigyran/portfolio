package com.aya.digital.core.ui.delegates.auth.chooser.description.model

import com.aya.digital.core.ui.adapters.base.DiffItem

class ChooserDescriptionUIModel : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is ChooserDescriptionUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is ChooserDescriptionUIModel
}