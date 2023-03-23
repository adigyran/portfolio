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
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineTwoLineLabelUIModel
import com.aya.digital.core.ui.delegates.components.labels.headlinetwoline.databinding.ItemHeadlineTwolineLabelBinding

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class HeadlineTwoLineLabelDelegate() :
    BaseDelegate<HeadlineTwoLineLabelUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HeadlineTwoLineLabelUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HeadlineTwoLineLabelUIModel> {
        val binding =
            ItemHeadlineTwolineLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemHeadlineTwolineLabelBinding) :
        BaseViewHolder<HeadlineTwoLineLabelUIModel>(binding.root) {

        var initialised = false

        override fun bind(item: HeadlineTwoLineLabelUIModel) {
            super.bind(item)
            if(!initialised)
            {
                binding.topTextTv.text = item.topText
                binding.bottomTextTv.text = item.bottomText
                initialised = true
            }
        }
    }

}
