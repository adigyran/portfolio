package com.aya.digital.core.ui.delegates.components.fields.emailphone.ui

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.databinding.ItemEmailphoneFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailPhoneFieldUIModel

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun emailPhoneFieldDelegate(delegateListeners: EmailPhoneDelegateListeners) = adapterDelegateViewBinding<EmailPhoneFieldUIModel,DiffItem, ItemEmailphoneFieldBinding>(
    { layoutInflater, root -> ItemEmailphoneFieldBinding.inflate(layoutInflater, root, false) }
) {

    var fieldSet = false

    binding.edField.doAfterTextChanged { text ->
        delegateListeners.inputFieldChangeListener(text.toString())
    }


/*    val inputFieldChangeListener = object : TextWatcher {
        var firstCall = true

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (firstCall) {
                firstCall = false
                return
            }
            //Если постоянно занулять ошибку то helperText исчезает
            if (!binding.textInputLayout.error.isNullOrBlank()) {
                binding.inputField.textInputLayout.error = null
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            delegateListeners.inputFieldChangeListener(s?.toString()?:"")
        }

    }*/
    bind {
        if (!fieldSet) {
            binding.tilField.hint = item.label
            binding.edField.setText(item.text)
            fieldSet = true
        }
      /*  binding.inputField.setHintText(item.label)
        binding.inputField.editText.removeTextChangedListener(inputFieldChangeListener)
        if(binding.inputField.editText.text.toString() != item.text)
        {binding.inputField.editText.setText(item.text)}
        binding.inputField.editText.addTextChangedListener(inputFieldChangeListener)*/

    }

  /*  onViewAttachedToWindow {
        binding.inputField.editText.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                binding.inputField.textInputLayout.error = item.error
            }
        }
    }

    onViewDetachedFromWindow { binding.inputField.editText.onFocusChangeListener = null }*/
}

class EmailPhoneDelegateListeners(val inputFieldChangeListener: ((String) -> Unit))
