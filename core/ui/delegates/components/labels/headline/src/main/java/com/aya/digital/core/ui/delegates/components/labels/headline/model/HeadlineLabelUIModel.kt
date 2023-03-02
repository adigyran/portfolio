package com.aya.digital.core.ui.delegates.components.labels.headline.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

data class HeadlineLabelUIModel(val labelText:String) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HeadlineLabelUIModel && this.labelText == newItem.labelText

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HeadlineLabelUIModel && this.labelText == newItem.labelText
}