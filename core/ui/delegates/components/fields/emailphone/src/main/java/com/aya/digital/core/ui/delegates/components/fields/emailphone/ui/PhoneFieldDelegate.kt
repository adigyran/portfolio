package com.aya.digital.core.ui.delegates.components.fields.emailphone.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.databinding.ItemEmailphoneFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.PhoneFieldUIModel

import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import timber.log.Timber

class PhoneFieldDelegate(private val delegateListeners: PhoneDelegateListeners) :
    BaseDelegate<PhoneFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is PhoneFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<PhoneFieldUIModel> {
        val binding =
            ItemEmailphoneFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemEmailphoneFieldBinding) :
        BaseViewHolder<PhoneFieldUIModel>(binding.root) {
        private lateinit var maskFormatWatcher: MaskFormatWatcher
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

            override fun afterTextChanged(s: Editable) = delegateListeners.inputFieldChangeListener(item.tag,maskFormatWatcher.mask.toUnformattedString())
        }

        private fun formattedValue(): String {
            val mask = item.mask
            mask.clear()
            mask.insertFront(item.text)
            return mask.toString()
        }


        override fun bind(item: PhoneFieldUIModel) {
            super.bind(item)
            if (!fieldSet) {
                maskFormatWatcher = MaskFormatWatcher(item.mask)
                binding.tilField.hint = item.label
                fieldSet = true
            }
            maskFormatWatcher.removeFromTextView()
            binding.edField.removeTextChangedListener(textWatcher)
            val formattedValue = formattedValue()
            val fieldText = binding.edField.text.toString()
            if (fieldText != formattedValue) {
                binding.edField.setText(formattedValue)
            }
            maskFormatWatcher.installOn(binding.edField)
            maskFormatWatcher.refreshMask(formattedValue)
            binding.edField.addTextChangedListener(textWatcher)
        }

        override fun onViewAttachedToWindow() {
            maskFormatWatcher.installOn(binding.edField)
        }

        override fun onViewRecycled() {
            maskFormatWatcher.removeFromTextView()
        }
    }

}

class PhoneDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
