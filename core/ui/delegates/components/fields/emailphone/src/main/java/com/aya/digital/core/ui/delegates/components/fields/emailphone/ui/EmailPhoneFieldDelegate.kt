package com.aya.digital.core.ui.delegates.components.fields.emailphone.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.databinding.ItemEmailphoneFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailPhoneFieldMode
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailPhoneFieldUIModel

import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class EmailPhoneFieldDelegate(private val delegateListeners: EmailPhoneDelegateListeners) :
    BaseDelegate<EmailPhoneFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is EmailPhoneFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<EmailPhoneFieldUIModel> {
        val binding =
            ItemEmailphoneFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemEmailphoneFieldBinding) :
        BaseViewHolder<EmailPhoneFieldUIModel>(binding.root) {
        private var maskFormatWatcher: MaskFormatWatcher? = null

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
                delegateListeners.inputFieldChangeListener(item.tag, getFieldValue(s))
        }


        private fun getFieldValue(s: Editable) = when(item.mode)
        {
            EmailPhoneFieldMode.EMAIL_MODE -> s.toString()
            EmailPhoneFieldMode.PHONE_MODE -> maskFormatWatcher?.mask?.toUnformattedString()?:""
        }
        private fun formattedValue(): String? {
            if(item.mode == EmailPhoneFieldMode.EMAIL_MODE) return item.text
            val mask = item.mask
            mask?.clear()
            mask?.insertFront(item.text)
            return mask.toString()
        }


        override fun bind(item: EmailPhoneFieldUIModel) {
            super.bind(item)
            if (!fieldSet) {
                item.mask?.let {
                    maskFormatWatcher = MaskFormatWatcher(item.mask)
                }
                binding.tilField.hint = item.label
                fieldSet = true
            }
            maskFormatWatcher?.removeFromTextView()
            binding.edField.removeTextChangedListener(textWatcher)
            val formattedValue = formattedValue()
            if (binding.edField.text.toString() != formattedValue) {
                binding.edField.setText(formattedValue)
            }
            maskFormatWatcher?.installOn(binding.edField)
            maskFormatWatcher?.refreshMask(formattedValue)
            binding.edField.addTextChangedListener(textWatcher)
        }

        override fun onViewAttachedToWindow() {
            maskFormatWatcher?.installOn(binding.edField)
        }

        override fun onViewRecycled() {
            maskFormatWatcher?.removeFromTextView()
        }
    }

}

class EmailPhoneDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
