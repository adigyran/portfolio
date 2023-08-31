package com.aya.digital.core.ui.delegates.home.homeitems.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesTitleUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeLastUpdatesTitleBinding

class HomeLastUpdatesTitleDelegate() :
    BaseDelegate<HomeLastUpdatesTitleUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeLastUpdatesTitleUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeLastUpdatesTitleUIModel> {
        val binding =
            ItemHomeLastUpdatesTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeLastUpdatesTitleBinding) :
        BaseViewHolder<HomeLastUpdatesTitleUIModel>(binding.root) {

        init {

        }
        override fun bind(item: HomeLastUpdatesTitleUIModel) {
            super.bind(item)
            binding.latestUpdatesTitle.text = item.text
        }
    }
}