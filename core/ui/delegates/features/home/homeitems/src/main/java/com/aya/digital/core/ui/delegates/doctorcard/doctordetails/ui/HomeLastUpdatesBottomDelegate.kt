package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.HomeButtonUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.HomeLastUpdatesBottomUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeButtonBinding
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeLastUpdatesBottomBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

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
        BaseViewHolder<HomeLastUpdatesBottomUIModel>(binding.root) {

        init {

        }
        override fun bind(item: HomeLastUpdatesBottomUIModel) {
            super.bind(item)

        }
    }
}