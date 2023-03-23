package com.aya.digital.core.ui.delegates.components.labels.headline.ui

import android.text.Spannable
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.labels.spannablehelper.model.SpannableHelperLabelUIModel
import com.aya.digital.core.ui.delegates.components.labels.spannablehelper.databinding.ItemSpannablehelperLabelBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class SpannableHelperLabelDelegate(private val delegateListeners: SpannableHelperLabelDelegateListeners) :
    BaseDelegate<SpannableHelperLabelUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is SpannableHelperLabelUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<SpannableHelperLabelUIModel> {
        val binding =
            ItemSpannablehelperLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemSpannablehelperLabelBinding) :
        BaseViewHolder<SpannableHelperLabelUIModel>(binding.root) {

        init {
            binding.textField.movementMethod = LinkTouchMovementMethod()
        }

        var isIntialised = false
        var spannableText :Spannable? = null
        override fun bind(item: SpannableHelperLabelUIModel) {
            super.bind(item)
            if(!isIntialised) {
                spannableText = spannableText ?: item.formattedText.createSpannableText(
                    binding.textField.context.colors[R.color.button_text_blue],
                    binding.textField.context.colors[R.color.button_bg_dark_blue],
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
                    binding.textField.context,
                    R.style.TextAppearance_App_Body_DescriptionMiniText,
                    R.style.TextAppearance_App_ButtonText_Default,
                    listOf(SpannableObject(item.spannableText) { delegateListeners.onSpanClick() })
                )
                binding.textField.text = spannableText
                isIntialised = true
            }
        }
    }

}
class SpannableHelperLabelDelegateListeners(val onSpanClick:()->Unit)
