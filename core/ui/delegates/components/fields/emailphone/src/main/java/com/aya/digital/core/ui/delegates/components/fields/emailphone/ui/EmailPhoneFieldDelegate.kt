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
    var valueSet = false

    binding.edField.doAfterTextChanged { text ->
        delegateListeners.inputFieldChangeListener(text.toString())
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

class EmailPhoneDelegateListeners(val inputFieldChangeListener: ((String) -> Unit))
