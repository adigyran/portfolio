package com.aya.digital.core.ui.delegates.auth.chooser.description.ui

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import com.aya.digital.core.ext.appendExtended
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.formatSpannable
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.base.utils.TouchableSpan
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ui.delegates.auth.chooser.description.model.ChooserDescriptionUIModel
import com.aya.digital.core.ui.delegates.features.auth.chooser.databinding.ItemChooserDescriptionBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun chooserDescriptionDelegate(delegateListeners: DescriptionDelegateListeners) =
    adapterDelegateViewBinding<ChooserDescriptionUIModel, DiffItem, ItemChooserDescriptionBinding>(
        { layoutInflater, root ->
            ItemChooserDescriptionBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

        //By registering, you agree to the Terms of Service, Privacy Policy, and Cookie Policy.
        val descriptionText = "By registering, you agree to the %s, %s, and %s.".formatSpannable(
            *createSpannableStrings(
                binding.root.context,
                delegateListeners
            )
        )
        binding.tvDescription.movementMethod = LinkTouchMovementMethod()
        bind {
            binding.tvDescription.text = descriptionText
        }


    }

fun createSpannableStrings(
    context: Context,
    delegateListeners: DescriptionDelegateListeners
): Array<CharSequence> {
    val spannables = mutableListOf<CharSequence>()
    spannables.add(
        SpannableStringBuilder()
            .appendExtended(
                "Terms of Service", listOf(
                    TouchableSpan(
                        context.colors[R.color.button_text_blue],
                        context.colors[R.color.button_bg_dark_blue]
                    )
                    {
                        delegateListeners.termOfServiceClick()
                    }
                ),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
    )
    spannables.add(
        SpannableStringBuilder()
            .appendExtended(
                "Privacy Policy", listOf(
                    TouchableSpan(
                        context.colors[R.color.button_text_blue],
                        context.colors[R.color.button_bg_dark_blue]
                    )
                    {
                        delegateListeners.privacyPolicyClick()
                    }
                ),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
    )
    spannables.add(
        SpannableStringBuilder()
            .appendExtended(
                "Cookie Policy", listOf(
                    TouchableSpan(
                        context.colors[R.color.button_text_blue],
                        context.colors[R.color.button_bg_dark_blue]
                    )
                    {
                        delegateListeners.cookiePolicyClick()
                    }
                ),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
    )
    return spannables.toTypedArray()
}

class DescriptionDelegateListeners(
    val termOfServiceClick: () -> Unit,
    val privacyPolicyClick: () -> Unit,
    val cookiePolicyClick: () -> Unit
)

