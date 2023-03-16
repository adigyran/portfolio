package com.aya.digital.core.ui.delegates.components.fields.selection.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.selection.databinding.ItemSelectionFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun selectionFieldDelegate(
    delegateListeners: SelectionFieldDelegateListeners,
    settable: Boolean = false
) = adapterDelegateViewBinding<SelectionFieldUIModel, DiffItem, ItemSelectionFieldBinding>(
    { layoutInflater, root -> ItemSelectionFieldBinding.inflate(layoutInflater, root, false) }
) {

    var fieldSet = false
    var valueSet = false

    binding.btn bindClick { delegateListeners.fieldClick(item.tag) }

    bind {
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

class SelectionFieldDelegateListeners(val fieldClick: (tag: Int) -> Unit)
