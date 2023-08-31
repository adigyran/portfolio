package com.aya.digital.core.ui.delegates.home.homeitems.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesTopUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeLastUpdatesTopBinding

class HomeLastUpdatesTopDelegate() :
    BaseDelegate<HomeLastUpdatesTopUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeLastUpdatesTopUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeLastUpdatesTopUIModel> {
        val binding =
            ItemHomeLastUpdatesTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeLastUpdatesTopBinding) :
        BaseViewHolder<HomeLastUpdatesTopUIModel>(binding.root),SectionHolder {

        init {

        }
        override fun bind(item: HomeLastUpdatesTopUIModel) {
            super.bind(item)

        }
        override fun getHomeSection(): HomeSection = HomeSection.Other

    }
}