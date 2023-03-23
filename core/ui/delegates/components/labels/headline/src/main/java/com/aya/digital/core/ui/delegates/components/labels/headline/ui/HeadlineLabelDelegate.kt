package com.aya.digital.core.ui.delegates.components.labels.headline.ui

import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.strings
import com.aya.digital.core.localisation.R
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineLabelUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.databinding.ItemHeadlineLabelBinding

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class HeadlineLabelDelegate(private val delegateListeners: HeadlineLabelDelegateListeners) :
    BaseDelegate<HeadlineLabelUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HeadlineLabelUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HeadlineLabelUIModel> {
        val binding =
            ItemHeadlineLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemHeadlineLabelBinding) :
        BaseViewHolder<HeadlineLabelUIModel>(binding.root) {

        init {
        }


        override fun bind(item: HeadlineLabelUIModel) {
            super.bind(item)
            binding.textField.text = item.labelText
        }
    }

}
class HeadlineLabelDelegateListeners()
