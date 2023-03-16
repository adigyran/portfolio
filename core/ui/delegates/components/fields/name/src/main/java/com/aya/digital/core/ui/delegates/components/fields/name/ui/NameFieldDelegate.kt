package com.aya.digital.core.ui.delegates.components.fields.name.model.ui

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.databinding.ItemNameFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun nameFieldDelegate(delegateListeners: NameFieldDelegateListeners, settable: Boolean = false) =
    adapterDelegateViewBinding<NameFieldUIModel, DiffItem, ItemNameFieldBinding>(
        { layoutInflater, root -> ItemNameFieldBinding.inflate(layoutInflater, root, false) }
    ) {

        var fieldSet = false
        var valueSet = false

        val textWatcher = object : TextWatcher {
            var firstCall = true
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (firstCall) {
                    firstCall = false
                    return
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun afterTextChanged(s: Editable) = delegateListeners.inputFieldChangeListener(item.tag,s.toString())
        }
        /*binding.edField.doAfterTextChanged { text ->
            delegateListeners.inputFieldChangeListener(item.tag, text.toString())
        }*/
        bind {
            if (!fieldSet) {
                binding.tilField.hint = item.label
                binding.tilField.suffixText = item.suffix
                fieldSet = true
            }
            binding.edField.removeTextChangedListener(textWatcher)
            if (binding.edField.text.toString() != item.text) {
                binding.edField.setText(item.text)
            }
            binding.edField.addTextChangedListener(textWatcher)
            /*  if (settable || !fieldSet) {
                  binding.tilField.hint = item.label
                  binding.tilField.suffixText = item.suffix
                  fieldSet = true
              }
              if(settable || !valueSet && item.text!=null && item.text!!.isNotEmpty() && item.text!=binding.edField.text.toString())
              {
                  binding.edField.setText(item.text)
                  valueSet = true
              }*/
        }

    }

class NameFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
