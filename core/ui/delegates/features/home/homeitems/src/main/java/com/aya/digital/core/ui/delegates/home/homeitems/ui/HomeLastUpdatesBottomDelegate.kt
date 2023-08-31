package com.aya.digital.core.ui.delegates.home.homeitems.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesBottomUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeLastUpdatesBottomBinding

class HomeLastUpdatesBottomDelegate() :
    BaseDelegate<HomeLastUpdatesBottomUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeLastUpdatesBottomUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeLastUpdatesBottomUIModel> {
        val binding =
            ItemHomeLastUpdatesBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeLastUpdatesBottomBinding) :
        BaseViewHolder<HomeLastUpdatesBottomUIModel>(binding.root),SectionHolder {

        init {

        }
        override fun bind(item: HomeLastUpdatesBottomUIModel) {
            super.bind(item)

        }
        override fun getHomeSection(): HomeSection = HomeSection.Other
    }
}