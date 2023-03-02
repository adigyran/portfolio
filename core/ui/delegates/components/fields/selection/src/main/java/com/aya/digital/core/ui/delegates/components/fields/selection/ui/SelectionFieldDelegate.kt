package com.aya.digital.core.ui.delegates.components.fields.selection.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.selection.databinding.ItemSelectionFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun selectionFieldDelegate(delegateListeners: SelectionFieldDelegateListeners) = adapterDelegateViewBinding<SelectionFieldUIModel,DiffItem,ItemSelectionFieldBinding>(
    { layoutInflater, root -> ItemSelectionFieldBinding.inflate(layoutInflater, root, false) }
) {

    var fieldSet = false

    binding.btn bindClick {delegateListeners.fieldClick(item.tag)}
    //binding.tilField bindClick {delegateListeners.fieldClick(item.tag)}

    bind {
        if (!fieldSet) {
            binding.tilField.hint = item.label
            fieldSet = true
        }
        binding.edField.setText(item.text)

        //  binding.inputField.setHintText(item.label)
       // binding.inputField.editText.setText(item.text)
    }

}

class SelectionFieldDelegateListeners(val fieldClick: (tag: Int) -> Unit)
