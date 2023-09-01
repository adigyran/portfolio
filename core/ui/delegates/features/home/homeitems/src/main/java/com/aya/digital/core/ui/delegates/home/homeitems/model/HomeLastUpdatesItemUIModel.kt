package com.aya.digital.core.ui.delegates.home.homeitems.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class HomeLastUpdatesItemUIModel(
    val text:String
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesItemUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesItemUIModel
                && newItem.text==this.text
}