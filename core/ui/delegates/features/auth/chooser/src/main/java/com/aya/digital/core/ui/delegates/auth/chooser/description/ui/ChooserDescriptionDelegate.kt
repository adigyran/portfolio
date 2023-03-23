package com.aya.digital.core.ui.delegates.auth.chooser.description.ui

import android.content.Context
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.designsystem.R.color
import com.aya.digital.core.designsystem.R.style as DesignSystemStyleR
import com.aya.digital.core.localisation.R.string as LocalisationR
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ext.strings
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.delegates.auth.chooser.description.model.ChooserDescriptionUIModel
import com.aya.digital.core.ui.delegates.features.auth.chooser.databinding.ItemChooserDescriptionBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class ChooserDescriptionDelegate(private val delegateListeners: DescriptionDelegateListeners) :
    BaseDelegate<ChooserDescriptionUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is ChooserDescriptionUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ChooserDescriptionUIModel> {
        val binding =
            ItemChooserDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemChooserDescriptionBinding) :
        BaseViewHolder<ChooserDescriptionUIModel>(binding.root) {

        init {
            binding.tvDescription.movementMethod = LinkTouchMovementMethod()
        }

        val descriptionText = with(binding.tvDescription.context)
        {
            strings[LocalisationR.auth_chooser_description_formatted].createSpannableText(
                colors[color.button_text_blue],
                colors[color.button_bg_dark_blue],
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
                binding.tvDescription.context,
                DesignSystemStyleR.TextAppearance_App_Body_DescriptionMiniText,
                DesignSystemStyleR.TextAppearance_App_ButtonText_Default,
                createSpannableObjects(
                    binding.root.context,
                    delegateListeners
                )
            )
        }

        override fun bind(item: ChooserDescriptionUIModel) {
            super.bind(item)

            binding.tvDescription.text = descriptionText
        }
    }

}


fun createSpannableObjects(
    context: Context,
    delegateListeners: DescriptionDelegateListeners
): List<SpannableObject> = with(context) {
    listOf(
        SpannableObject(strings[LocalisationR.terms_of_service_button]) { delegateListeners.termOfServiceClick() },
        SpannableObject(strings[LocalisationR.privacy_policy_button]) { delegateListeners.privacyPolicyClick() },
        SpannableObject(strings[LocalisationR.cookie_policy_button]) { delegateListeners.cookiePolicyClick() }
    )
}

class DescriptionDelegateListeners(
    val termOfServiceClick: () -> Unit,
    val privacyPolicyClick: () -> Unit,
    val cookiePolicyClick: () -> Unit
)

