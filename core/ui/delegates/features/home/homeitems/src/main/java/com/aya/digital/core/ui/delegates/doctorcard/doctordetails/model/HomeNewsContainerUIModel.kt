package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model

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