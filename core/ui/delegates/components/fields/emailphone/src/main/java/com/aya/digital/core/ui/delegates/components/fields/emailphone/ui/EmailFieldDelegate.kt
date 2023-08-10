package com.aya.digital.core.ui.delegates.components.fields.emailphone.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.databinding.ItemEmailphoneFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailFieldUIModel

class EmailFieldDelegate(private val delegateListeners: EmailDelegateListeners) :
    BaseDelegate<EmailFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is EmailFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<EmailFieldUIModel> {
        val binding =
            ItemEmailphoneFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemEmailphoneFieldBinding) :
        BaseViewHolder<EmailFieldUIModel>(binding.root) {

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

            override fun afterTextChanged(s: Editable) =
                delegateListeners.inputFieldChangeListener(item.tag, s.toString())
        }




        override fun bind(item: EmailFieldUIModel) {
            super.bind(item)
            if (!fieldSet) {
                binding.tilField.hint = item.label
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

class EmailDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
