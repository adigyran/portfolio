package com.aya.digital.core.ui.delegates.components.fields.validated.ui

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import ccom.aya.digital.core.ui.delegates.components.fields.validated.databinding.ItemValidatedFieldBinding
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.validated.model.ValidatedFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class ValidatedFieldDelegate(private val delegateListeners: ValidatedFieldDelegateListeners) :
    BaseDelegate<ValidatedFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is ValidatedFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ValidatedFieldUIModel> {
        val binding =
            ItemValidatedFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemValidatedFieldBinding) :
        BaseViewHolder<ValidatedFieldUIModel>(binding.root) {
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

            override fun afterTextChanged(s: Editable) = delegateListeners.inputFieldChangeListener(item.tag,s.toString())
        }

        init {

        }

        override fun bind(item: ValidatedFieldUIModel) {
            super.bind(item)

            if (!fieldSet) {
                maskFormatWatcher = MaskFormatWatcher(item.mask)
                binding.tilField.hint = item.label
                binding.tilField.suffixText = item.suffix
                binding.edField.setInputType(item.inputType);
                fieldSet = true
            }
            maskFormatWatcher.removeFromTextView()
            binding.edField.removeTextChangedListener(textWatcher)
            if (binding.edField.text.toString() != item.text) {
                binding.edField.setText(item.text)
            }
            maskFormatWatcher.installOn(binding.edField)
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

class ValidatedFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int, text: String) -> Unit))
