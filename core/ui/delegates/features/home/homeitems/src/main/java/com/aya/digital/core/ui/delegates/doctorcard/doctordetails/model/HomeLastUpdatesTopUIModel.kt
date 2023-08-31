package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model

import com.aya.digital.core.ui.adapters.base.DiffItem

class HomeLastUpdatesTopUIModel(

) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesTopUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesTopUIModel
}