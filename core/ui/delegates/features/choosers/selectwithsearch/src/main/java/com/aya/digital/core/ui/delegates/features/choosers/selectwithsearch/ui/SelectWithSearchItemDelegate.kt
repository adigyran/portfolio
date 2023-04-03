package com.aya.digital.core.ui.delegates.features.choosers.selectwithsearch.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ext.drawables
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder

import com.aya.digital.core.ui.delegates.features.choosers.selectwithsearch.model.SelectWithSearchItemUIModel
import com.aya.digital.core.ui.delegates.features.choosers.selectwithsearch.databinding.ItemSelectWithSearchBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class SelectWithSearchItemDelegate(private val itemClick: (id: Int) -> Unit) :
    BaseDelegate<SelectWithSearchItemUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is SelectWithSearchItemUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<SelectWithSearchItemUIModel> {
        val binding =
            ItemSelectWithSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemSelectWithSearchBinding) :
        BaseViewHolder<SelectWithSearchItemUIModel>(binding.root) {

        init {
            binding.root bindClick { itemClick(item.id) }
        }
        private fun getDrawable(selected: Boolean): Drawable {
            val id = if (selected) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox
            return binding.selectedBox.context.drawables[id]
        }
        override fun bind(item: SelectWithSearchItemUIModel) {
            super.bind(item)
            binding.textView.text = item.text
            binding.selectedBox.setImageDrawable(getDrawable(item.selected))
        }
    }
}