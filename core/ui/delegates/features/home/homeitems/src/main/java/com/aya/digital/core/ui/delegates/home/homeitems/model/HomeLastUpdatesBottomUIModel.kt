package com.aya.digital.core.ui.delegates.home.homeitems.model

import com.aya.digital.core.ui.adapters.base.DiffItem

class HomeLastUpdatesBottomUIModel(

) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesBottomUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is HomeLastUpdatesBottomUIModel
}