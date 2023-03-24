package com.aya.digital.core.ui.delegates.components.fields.name.model.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.ValidatedNumberFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.validatednumber.databinding.ItemValidatedNumberFieldBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class ValidatedNumberFieldDelegate(private val delegateListeners: ValidatedNumberFieldDelegateListeners) :
    BaseDelegate<ValidatedNumberFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is ValidatedNumberFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ValidatedNumberFieldUIModel> {
        val binding =
            ItemValidatedNumberFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemValidatedNumberFieldBinding) :
        BaseViewHolder<ValidatedNumberFieldUIModel>(binding.root) {

        private var fieldSet = false

        private val textWatcher = object : TextWatcher {
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

        override fun bind(item: ValidatedNumberFieldUIModel) {
            super.bind(item)

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
        }
    }
}

class ValidatedNumberFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
