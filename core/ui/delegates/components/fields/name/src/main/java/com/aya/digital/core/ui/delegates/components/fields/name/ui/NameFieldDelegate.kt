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

        binding.edField.doAfterTextChanged { text ->
            delegateListeners.inputFieldChangeListener(item.tag, text.toString())
        }
        bind {
            if (!fieldSet) {
                binding.tilField.hint = item.label
                binding.edField.setText(item.text)
                binding.tilField.suffixText = item.suffix
                fieldSet = true
            }
            binding.edField.setText(item.text)
            binding.tilField.suffixText = item.suffix
        }

    }

class NameFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
