package com.aya.digital.core.ui.delegates.home.homeitems.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class HomeButtonUIModel(
    val tag: Int,
    val title: String,
    val descr: String,
    val iconId: Int,
    val enabled: Boolean
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeButtonUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeButtonUIModel
                && newItem.tag==this.tag
                && newItem.title==this.title
                && newItem.descr==this.descr
                && newItem.iconId==this.iconId
                && newItem.enabled==this.enabled
}