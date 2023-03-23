package com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui

import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.strings
import com.aya.digital.core.localisation.R
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.model.ChooserButtonsUIModel
import com.aya.digital.core.ui.delegates.auth.chooser.description.model.ChooserDescriptionUIModel
import com.aya.digital.core.ui.delegates.auth.chooser.description.ui.DescriptionDelegateListeners
import com.aya.digital.core.ui.delegates.auth.chooser.description.ui.createSpannableObjects
import com.aya.digital.core.ui.delegates.features.auth.chooser.databinding.ItemChooserButtonsBinding
import com.aya.digital.core.ui.delegates.features.auth.chooser.databinding.ItemChooserDescriptionBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class ChooserButtonsDelegate(private val delegateListeners: ButtonsDelegateListeners) :
    BaseDelegate<ChooserButtonsUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is ChooserButtonsUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ChooserButtonsUIModel> {
        val binding =
            ItemChooserButtonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemChooserButtonsBinding) :
        BaseViewHolder<ChooserButtonsUIModel>(binding.root) {

        init {
            binding.btnSignin bindClick { delegateListeners.signInClick() }
            binding.btnSignup bindClick { delegateListeners.signUpClick() }
        }

        override fun bind(item: ChooserButtonsUIModel) {
            super.bind(item)

        }
    }
}


class ButtonsDelegateListeners(val signInClick: () -> Unit, val signUpClick: () -> Unit)
