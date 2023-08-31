package com.aya.digital.core.ui.delegates.home.homeitems.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class HomeClinicsUIModel(
    val clinicsCount: String,
    val mapImageId:Int
    ) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeClinicsUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeClinicsUIModel
                && newItem.clinicsCount==this.clinicsCount
                && newItem.mapImageId==this.mapImageId
}