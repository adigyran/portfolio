package com.aya.digital.core.ui.delegates.components.fields.password.ui

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.password.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.password.databinding.ItemPasswordFieldBinding

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun passwordFieldDelegate(delegateListeners: PasswordFieldDelegateListeners) = adapterDelegateViewBinding<PasswordFieldUIModel,DiffItem, ItemPasswordFieldBinding>(
    { layoutInflater, root -> ItemPasswordFieldBinding.inflate(layoutInflater, root, false) }
) {

    var fieldSet = false

    binding.edField.doAfterTextChanged { text ->
        delegateListeners.inputFieldChangeListener(item.tag,text.toString())
    }
  /*  val inputFieldChangeListener = object : TextWatcher {
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
            delegateListeners.inputFieldChangeListener(item.tag,s?.toString()?:"")
        }

    }*/
    bind {
        if (!fieldSet) {
            binding.tilField.hint = item.label
            binding.edField.setText(item.text)
            fieldSet = true
        }
        /* binding.inputField.setHintText(item.label)
         binding.inputField.editText.removeTextChangedListener(inputFieldChangeListener)
         if(binding.inputField.editText.text.toString() != item.text)
         {binding.inputField.editText.setText(item.text)}
         binding.inputField.editText.addTextChangedListener(inputFieldChangeListener)*/

    }

    /*onViewAttachedToWindow {
        binding.inputField.editText.setOnFocusChangeListener{ _, focused ->
            if (!focused) {
                binding.inputField.textInputLayout.error = item.error
            }
        }
    }

    onViewDetachedFromWindow { binding.inputField.editText.onFocusChangeListener = null }*/
}

class PasswordFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int,text:String) -> Unit))
