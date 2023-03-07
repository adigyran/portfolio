package com.aya.digital.core.ui.delegates.components.fields.name.model.ui

import androidx.core.widget.doAfterTextChanged
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.ValidatedNumberFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.validatednumber.databinding.ItemValidatedNumberFieldBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun validatedNumberFieldDelegate(delegateListeners: ValidatedNumberFieldDelegateListeners) =
    adapterDelegateViewBinding<ValidatedNumberFieldUIModel, DiffItem, ItemValidatedNumberFieldBinding>(
        { layoutInflater, root -> ItemValidatedNumberFieldBinding.inflate(layoutInflater, root, false) }
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
            if(!valueSet && item.text!=null && item.text!!.isNotBlank() )
            {
                binding.edField.setText(item.text)
                valueSet = true
            }
        }

    }

class ValidatedNumberFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
