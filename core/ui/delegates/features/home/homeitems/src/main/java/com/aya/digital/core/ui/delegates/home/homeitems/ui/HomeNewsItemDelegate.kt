package com.aya.digital.core.ui.delegates.home.homeitems.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeNewsUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeNewsItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class HomeNewsItemDelegate() :
    BaseDelegate<HomeNewsUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeNewsUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeNewsUIModel> {
        val binding =
            ItemHomeNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeNewsItemBinding) :
        BaseViewHolder<HomeNewsUIModel>(binding.root) {

        init {
        }
        override fun bind(item: HomeNewsUIModel) {
            super.bind(item)
            binding.newsTitle.text = item.title
            binding.newsDate.text = item.date
            Glide
                .with(binding.newsImage)
                .load(item.image)
                .transform(
                    RoundedCorners(24.dpToPx())
                )
                .dontAnimate()
                .into(binding.newsImage)

        }
    }
}