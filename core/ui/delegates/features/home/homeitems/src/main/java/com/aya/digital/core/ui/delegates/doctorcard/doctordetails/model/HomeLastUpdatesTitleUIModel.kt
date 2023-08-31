package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class HomeLastUpdatesTitleUIModel(
    val text:String
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesTitleUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesTitleUIModel
                && newItem.text==this.text
}