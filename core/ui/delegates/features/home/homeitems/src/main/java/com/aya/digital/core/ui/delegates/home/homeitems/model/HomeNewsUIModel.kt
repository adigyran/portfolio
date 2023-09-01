package com.aya.digital.core.ui.delegates.home.homeitems.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class HomeNewsUIModel(
    val id: Int,
    val title: String,
    val date:String,
    val image:String?
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeNewsUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeNewsUIModel
                && newItem.id==this.id
                && newItem.title==this.title
                && newItem.date==this.date
                && newItem.image==this.image
}