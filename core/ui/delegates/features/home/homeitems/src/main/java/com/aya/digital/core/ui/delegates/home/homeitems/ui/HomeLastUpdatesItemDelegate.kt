package com.aya.digital.core.ui.delegates.home.homeitems.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesItemUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeLastUpdatesItemBinding

class HomeLastUpdatesItemDelegate() :
    BaseDelegate<HomeLastUpdatesItemUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeLastUpdatesItemUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeLastUpdatesItemUIModel> {
        val binding =
            ItemHomeLastUpdatesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeLastUpdatesItemBinding) :
        BaseViewHolder<HomeLastUpdatesItemUIModel>(binding.root),SectionHolder {

        init {

        }
        override fun bind(item: HomeLastUpdatesItemUIModel) {
            super.bind(item)
            binding.latestUpdatesText.text = item.text
        }
        override fun getHomeSection(): HomeSection = HomeSection.Other

    }
}