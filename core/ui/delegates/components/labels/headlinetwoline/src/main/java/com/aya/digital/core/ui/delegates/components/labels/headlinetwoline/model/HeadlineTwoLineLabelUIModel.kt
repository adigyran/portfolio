package com.aya.digital.core.ui.delegates.components.labels.headline.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class HeadlineTwoLineLabelUIModel(val topText: String, val bottomText: String) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HeadlineTwoLineLabelUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HeadlineTwoLineLabelUIModel
                && this.topText == newItem.topText
                && this.bottomText == newItem.bottomText
}