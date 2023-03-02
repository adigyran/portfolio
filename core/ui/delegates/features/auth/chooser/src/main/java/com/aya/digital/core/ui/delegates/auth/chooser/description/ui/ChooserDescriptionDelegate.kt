package com.aya.digital.core.ui.delegates.auth.chooser.description.ui

import android.content.Context
import android.text.Spanned
import com.aya.digital.core.ui.base.ext.colors
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.designsystem.R.color
import com.aya.digital.core.localisation.R.string.*
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.ext.strings
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
        val descriptionText =
            context.strings[auth_chooser_description_formatted].createSpannableText(
                context.colors[color.button_text_blue],
                context.colors[color.button_bg_dark_blue],
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
                createSpannableObjects(
                    binding.root.context,
                    delegateListeners
                )
            )
        binding.tvDescription.movementMethod = LinkTouchMovementMethod()
        bind {
            binding.tvDescription.text = descriptionText
        }


    }

fun createSpannableObjects(
    context: Context,
    delegateListeners: DescriptionDelegateListeners
): List<SpannableObject> = with(context) {
    listOf(
        SpannableObject(strings[terms_of_service_button], { delegateListeners.termOfServiceClick() }),
        SpannableObject(strings[privacy_policy_button], { delegateListeners.privacyPolicyClick() }),
        SpannableObject(strings[cookie_policy_button], { delegateListeners.cookiePolicyClick() })
    )
}

class DescriptionDelegateListeners(
    val termOfServiceClick: () -> Unit,
    val privacyPolicyClick: () -> Unit,
    val cookiePolicyClick: () -> Unit
)

