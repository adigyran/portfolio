package com.aya.digital.core.ui.delegates.home.homeitems.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeClinicsBinding
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeNewsUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeNewsItemBinding
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeClinicsUIModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class HomeClinicsItemDelegate() :
    BaseDelegate<HomeClinicsUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeClinicsUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeClinicsUIModel> {
        val binding =
            ItemHomeClinicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeClinicsBinding) :
        BaseViewHolder<HomeClinicsUIModel>(binding.root) {

        init {
        }
        override fun bind(item: HomeClinicsUIModel) {
            super.bind(item)
            binding.clinicsCount.text = item.clinicsCount
            Glide
                .with(binding.mapImage)
                .load(item.mapImageId)
                .transform(
                    RoundedCorners(24.dpToPx())
                )
                .dontAnimate()
                .into(binding.mapImage)

        }
    }
}