package com.aya.digital.core.ui.delegates.components.labels.headline.ui

import android.text.Spannable
import android.text.Spanned
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.labels.spannablehelper.model.SpannableHelperLabelUIModel
import com.aya.digital.core.ui.delegates.components.labels.spannablehelper.databinding.ItemSpannablehelperLabelBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun spannableHelperLabelDelegate(delegateListeners: SpannableHelperLabelDelegateListeners) =
    adapterDelegateViewBinding<SpannableHelperLabelUIModel, DiffItem, ItemSpannablehelperLabelBinding>(
        { layoutInflater, root -> ItemSpannablehelperLabelBinding.inflate(layoutInflater, root, false) }
    ) {

        var isIntialised = false
        var spannableText :Spannable? = null
        binding.textField.movementMethod = LinkTouchMovementMethod()
        bind {
            if(!isIntialised) {
                spannableText = spannableText ?: item.formattedText.createSpannableText(
                    context.colors[R.color.button_text_blue],
                    context.colors[R.color.button_bg_dark_blue],
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
                    listOf(SpannableObject(item.spannableText) { delegateListeners.onSpanClick() })
                )
                binding.textField.text = spannableText
                isIntialised = true
            }
        }
    }

class SpannableHelperLabelDelegateListeners(val onSpanClick:()->Unit)
