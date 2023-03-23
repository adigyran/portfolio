package com.aya.digital.core.ui.delegates.components.fields.selection.ui

import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.strings
import com.aya.digital.core.localisation.R
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.fields.selection.databinding.ItemSelectionFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class SelectionFieldDelegate(private val delegateListeners: SelectionFieldDelegateListeners) :
    BaseDelegate<SelectionFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is SelectionFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<SelectionFieldUIModel> {
        val binding =
            ItemSelectionFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemSelectionFieldBinding) :
        BaseViewHolder<SelectionFieldUIModel>(binding.root) {

        init {
            binding.btn bindClick { delegateListeners.fieldClick(item.tag) }
        }

        var fieldSet = false

        override fun bind(item: SelectionFieldUIModel) {
            super.bind(item)

            if (!fieldSet) {
                binding.tilField.hint = item.label
                item.endIconRes?.let {
                    binding.tilField.setEndIconDrawable(it)
                }
                fieldSet = true
            }
            if (item.text != null && item.text!!.isNotEmpty() && item.text != binding.edField.text.toString()) {
                binding.edField.setText(item.text)
            }
        }
    }
}

class SelectionFieldDelegateListeners(val fieldClick: (tag: Int) -> Unit)
