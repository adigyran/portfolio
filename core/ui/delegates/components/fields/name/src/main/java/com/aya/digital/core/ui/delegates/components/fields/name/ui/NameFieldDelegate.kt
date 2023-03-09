package com.aya.digital.core.ui.delegates.components.fields.name.model.ui

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.databinding.ItemNameFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun nameFieldDelegate(delegateListeners: NameFieldDelegateListeners) =
    adapterDelegateViewBinding<NameFieldUIModel, DiffItem, ItemNameFieldBinding>(
        { layoutInflater, root -> ItemNameFieldBinding.inflate(layoutInflater, root, false) }
    ) {

        var fieldSet = false
        var valueSet = false

        binding.edField.doAfterTextChanged { text ->
            delegateListeners.inputFieldChangeListener(item.tag, text.toString())
        }
        bind {
            if (!fieldSet) {
                binding.tilField.hint = item.label
                binding.tilField.suffixText = item.suffix
                fieldSet = true
            }
            if(!valueSet && item.text!=null && item.text!!.isNotEmpty() && item.text!=binding.edField.text.toString())
            {
                binding.edField.setText(item.text)
                valueSet = true
            }
        }

    }

class NameFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
