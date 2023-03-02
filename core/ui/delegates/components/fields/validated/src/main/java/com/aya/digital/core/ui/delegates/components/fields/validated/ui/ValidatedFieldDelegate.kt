package com.aya.digital.core.ui.delegates.components.fields.validated.ui

import android.text.Editable
import android.text.TextWatcher
import ccom.aya.digital.core.ui.delegates.components.fields.validated.databinding.ItemValidatedFieldBinding
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.validated.model.ValidatedFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun validatedFieldDelegate(delegateListeners: ValidatedFieldDelegateListeners) = adapterDelegateViewBinding<ValidatedFieldUIModel,DiffItem, ItemValidatedFieldBinding>(
    { layoutInflater, root -> ItemValidatedFieldBinding.inflate(layoutInflater, root, false) }
) {
    val inputFieldChangeListener = object : TextWatcher {
        var firstCall = true

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (firstCall) {
                firstCall = false
                return
            }
            //Если постоянно занулять ошибку то helperText исчезает
            if (!binding.inputField.textInputLayout.error.isNullOrBlank()) {
                binding.inputField.textInputLayout.error = null
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            delegateListeners.inputFieldChangeListener(s?.toString()?:"")
        }

    }
    bind {
        binding.inputField.setHintText(item.label)
        binding.inputField.editText.removeTextChangedListener(inputFieldChangeListener)
        if(binding.inputField.editText.text.toString() != item.text)
        {binding.inputField.editText.setText(item.text)}
        binding.inputField.editText.addTextChangedListener(inputFieldChangeListener)

    }

    onViewAttachedToWindow {
        binding.inputField.editText.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                binding.inputField.textInputLayout.error = item.error
            }
        }
    }

    onViewDetachedFromWindow { binding.inputField.editText.onFocusChangeListener = null }
}

class ValidatedFieldDelegateListeners(val inputFieldChangeListener: ((String) -> Unit))
