package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.HomeButtonUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeButtonBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class HomeButtonItemDelegate(val onButtonClick:(tag:Int) -> Unit) :
    BaseDelegate<HomeButtonUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeButtonUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeButtonUIModel> {
        val binding =
            ItemHomeButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeButtonBinding) :
        BaseViewHolder<HomeButtonUIModel>(binding.root) {

        init {
            binding.root bindClick {onButtonClick(item.tag)}
        }
        override fun bind(item: HomeButtonUIModel) {
            super.bind(item)
            binding.btnTitle.text = item.title
            binding.btnDescr.text = item.descr
            Glide
                .with(binding.buttonImage)
                .load(item.iconId)
                .dontAnimate()
                .into(binding.buttonImage)
        }
    }
}