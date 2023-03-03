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
    var valueSet = false

    binding.edField.doAfterTextChanged { text ->
        delegateListeners.inputFieldChangeListener(item.tag,text.toString())
    }
    bind {
        if (!fieldSet) {
            binding.tilField.hint = item.label
            fieldSet = true
        }
        if(!valueSet && item.text!=null && item.text!!.isNotBlank() )
        {
            binding.edField.setText(item.text)
            valueSet = true
        }
    }

}

class PasswordFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int,text:String) -> Unit))
