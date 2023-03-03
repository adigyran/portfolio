package com.aya.digital.core.ui.delegates.components.fields.selection.ui

import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.dropdown.databinding.ItemDropdownFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.selection.model.DropdownFieldUIModel
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun dropDownFieldDelegate(delegateListeners: DropDownFieldDelegateListeners) = adapterDelegateViewBinding<DropdownFieldUIModel,DiffItem,ItemDropdownFieldBinding>(
    { layoutInflater, root -> ItemDropdownFieldBinding.inflate(layoutInflater, root, false) }
) {

    var fieldSet = false


    (binding.edField as? MaterialAutoCompleteTextView)?.setOnItemClickListener { adapterView, view, i, l ->
        delegateListeners.itemSelected(binding.edField.text.toString())
    }
    bind {
        if (!fieldSet) {
            binding.tilField.hint = item.label
            item.items?.let {
                (binding.edField as? MaterialAutoCompleteTextView)?.setSimpleItems(it.toTypedArray())
            }
            fieldSet = true
        }
        binding.edField.setText(item.text)
    }

}

class DropDownFieldDelegateListeners(val itemSelected: (selectedItem: String) -> Unit)
