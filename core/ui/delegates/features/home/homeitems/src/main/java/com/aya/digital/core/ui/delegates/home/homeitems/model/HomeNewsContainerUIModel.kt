package com.aya.digital.core.ui.delegates.home.homeitems.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class HomeNewsContainerUIModel(
    val news:List<HomeNewsUIModel>
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeNewsContainerUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeNewsContainerUIModel
                && newItem.news==this.news
}